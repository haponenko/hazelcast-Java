import java.io.Serializable;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class PessimisticLocking {
    /**
     * Что бы не было условий гонок, используем пессимистическую блокировку
     * когда хотим создать запись в мап, просто блокируем ее, пока не закончим свою запись
     * 
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception {
    	/*
    	 ClientConfig clientConfig = new ClientConfig();
	        //clientConfig.addAddress("192.168.1.38:5701");
	        clientConfig.addAddress("192.168.0.107:5701");
	        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
	       */
    	HazelcastInstance hz = Hazelcast.newHazelcastInstance();;
	        IMap<String, Value> map = hz.getMap( "map" );
    	
       
        String key = "1";
        map.putIfAbsent( key, new Value() );
        System.out.println( "Starting" );
        //Thread.sleep( 1000 );
        //System.out.println( "After ..." );
        for ( int k = 0; k < 1000; k++ ) {
            map.lock( key );
            try {
                Value value = map.get( key );
                Thread.sleep( 10 );
                value.amount++;
                map.put( key, value );
            } finally {
                map.unlock( key );
            }
        }
        System.out.println( "Finished! Result = " + map.get( key ).amount );
    }

    static class Value implements Serializable {
        public int amount;
    }
}
