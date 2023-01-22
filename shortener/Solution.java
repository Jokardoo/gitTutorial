package test.shortener;


import test.shortener.strategy.HashMapStorageStrategy;
import test.shortener.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main (String[] args) {
        testStrategy(new HashMapStorageStrategy(), 1000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keySet = new HashSet<>();

        for (String str : strings) {
            keySet.add(shortener.getId(str));
        }
        return keySet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();

        for (Long key : keys) {
            stringSet.add(shortener.getString(key));
        }
        return stringSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = new HashSet<>();

        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Shortener s = new Shortener(strategy);

        Date start = new Date();
        Set<Long> keySet = new HashSet<>(getIds(s, stringSet));
        Date end = new Date();

        long time = end.getTime() - start.getTime();
        Helper.printMessage(Long.toString(time));

        Date startStr = new Date();
        Set<String> resultSet = getStrings(s, keySet);
        Date endStr = new Date();

        Long time2 = startStr.getTime() - endStr.getTime();
        Helper.printMessage(Long.toString(time2));

        if (resultSet.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }


    }
}