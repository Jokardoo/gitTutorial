package test.shortener.tests;


import org.junit.Assert;
import org.junit.Test;

import test.shortener.Helper;
import test.shortener.Shortener;
import test.shortener.strategy.HashBiMapStorageStrategy;
import test.shortener.strategy.HashMapStorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    @Test
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String s : strings)
            ids.add(shortener.getId(s));
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long l : ids) {
            strings.add(shortener.getString(l));
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> keySet1 = new HashSet<Long>();
        Set<Long> keySet2 = new HashSet<Long>();
        long time1 = getTimeToGetIds(shortener1, origStrings, keySet1);
        long time2 = getTimeToGetIds(shortener2, origStrings, keySet2);

        Set<String> StringSet1 = new HashSet<>();
        Set<String> StringSet2 = new HashSet<>();
        long time3 = getTimeToGetStrings(shortener1, keySet1, StringSet1);
        long time4 = getTimeToGetStrings(shortener2, keySet2, StringSet2);

        Assert.assertTrue(time1 > time2);
        Assert.assertEquals(time3, time4, 30);
    }

}

