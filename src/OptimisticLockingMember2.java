import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class OptimisticLockingMember2 {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap("map");
        String key = "1";

        System.out.println("Starting");
        for (int k = 0; k < 1000; k++) {
            if (k % 10 == 0) System.out.println("At: " + k);
            for (; ; ) {
                Value oldValue = map.get(key);
                Value newValue = new Value(oldValue);
                Thread.sleep(10);
                newValue.amount++;
                if (map.replace(key, oldValue, newValue))
                    break;
            }
        }
        System.out.println("Finished! Result = " + map.get(key).amount);
    }
}
