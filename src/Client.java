
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
	
public class Client {
		    
	    public static void main(String[] args) throws Exception {
	        HazelcastInstance client = HazelcastClient.newHazelcastClient();
	        System.out.println("Size of keyset = " +client.getMap("customers").keySet().size());     
	    }
}