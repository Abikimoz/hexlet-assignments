package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> currentData = storage.toMap();
        for (Map.Entry<String, String> entry : currentData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
