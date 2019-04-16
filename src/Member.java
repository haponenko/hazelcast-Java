import com.hazelcast.config.Config;
import java.util.Map;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Member {

    public static void main(String[] args) throws Exception {
    	
    	MapConfig mapcfg = new MapConfig("customers");
    	mapcfg.setBackupCount(2);
    	Config cfg = new Config().addMapConfig(mapcfg);
   
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
        System.out.println("Hazelcast Member instance is running!");
       
      
        Map<Integer, String>  mapCustomers = hz.getMap("customers");
        mapCustomers.put(1, "A");
        mapCustomers.put(2, "B");
        mapCustomers.put(3, "C");
        for (int i = 4; i<=1000; i++){
        	mapCustomers.put(i, "someone");   
        } 
      
             
        }
    }
