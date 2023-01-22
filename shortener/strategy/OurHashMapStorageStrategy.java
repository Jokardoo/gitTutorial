package test.shortener.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    static int hash(Long k) {
        return k.hashCode();
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    static Entry getEntry(Long key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            return entry;
        }
        return null;
    }

    void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;

        for (Entry e : table) {
            while (e != null) {
                Entry next = e.next;
                int indexInNewTable = indexFor(hash(e.getKey()), newCapacity);
                e.next = newTable[indexInNewTable];
                newTable[indexInNewTable] = e;
                e = next;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (size >= threshold) {
            resize(2 * threshold);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry next = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, next);
        size++;
    }

    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    public boolean containsValue(String value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (Entry e = table[index]; e != null; e = e.next) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, index);
    }

    public final Long getKey(String value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    public final String getValue(Long key) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getKey() == key) {
                    return e.getValue();
                }
            }
        }
        return null;
    }
}