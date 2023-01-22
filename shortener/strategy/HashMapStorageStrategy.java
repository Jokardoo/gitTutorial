package test.shortener.strategy;

import java.util.HashMap;

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    public void put(Long key, String value) {
        data.put(key, value);
    }

    public Long getKey(String value) {
        for (Long l : data.keySet()) {
            if (data.get(l).equals(value)) {
                return l;
            }
        }
        return null;
    }

    public String getValue(Long key) {
        return data.get(key);
    }
}