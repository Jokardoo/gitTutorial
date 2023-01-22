package test.shortener.tests;


import org.junit.Assert;
import org.junit.Test;


import test.shortener.Shortener;
import test.shortener.strategy.HashMapStorageStrategy;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String testString1 = "testString1";
        String testString2 = "testString2abc";
        String testString3 = "testString1";

        Long testId1 = shortener.getId(testString1);
        Long testId2 = shortener.getId(testString2);
        Long testId3 = shortener.getId(testString3);

        Assert.assertEquals(testString1, shortener.getString(testId1));
        Assert.assertEquals(testString2, shortener.getString(testId2));
        Assert.assertEquals(testString3, shortener.getString(testId3));

        Assert.assertNotEquals(testId1, testId2);
        Assert.assertNotEquals(testId2, testId3);
        Assert.assertEquals(testId1, testId3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

}