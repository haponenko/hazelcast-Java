import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.ReplicatedMapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ReplicatedMap;

public class MapReplication {
	/**
	 * создаем кластер из 3х нод и реплецированной коллекции
	 * демонстрируем, что на всех нодах данные одни и те же 
	 * @param args
	 */
	public static void main(String[] args) {
        Config cfg = new Config();
     
        ReplicatedMapConfig replicatedMapConfig =cfg.getReplicatedMapConfig( "default" );

        replicatedMapConfig.setInMemoryFormat( InMemoryFormat.BINARY );
        
        HazelcastInstance instance1 = Hazelcast.newHazelcastInstance(cfg);
        HazelcastInstance instance2 = Hazelcast.newHazelcastInstance(cfg);
        HazelcastInstance instance3 = Hazelcast.newHazelcastInstance(cfg);
        ReplicatedMap<Integer, String> mapCustomers = instance1.getReplicatedMap("customers");
          
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");
        
        for (int i = 4; i<=1000; i++){
    	mapCustomers.put(i, "someone");
    }        
        System.out.println("Replication of our map: ");
       
        System.out.println("Map keys quantity on Instance1: " + instance1.getReplicatedMap("customers").keySet().size());
        System.out.println("Show map keys on Instance1: " + instance1.getReplicatedMap("customers").keySet());
        System.out.println();
        System.out.println("Map keys quantity on Instance2: " + instance2.getReplicatedMap("customers").keySet().size());
        System.out.println("Show map keys on Instance2: " + instance2.getReplicatedMap("customers").keySet());
        System.out.println();
        System.out.println("Map keys quantity on Instance3: " + instance3.getReplicatedMap("customers").keySet().size());
        System.out.println("Show map keys on Instance3: " + instance3.getReplicatedMap("customers").keySet()); 
    }
}

