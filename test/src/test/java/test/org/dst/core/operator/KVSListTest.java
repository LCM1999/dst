package test.org.dst.core.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dst.core.KVStoreImpl;
import org.dst.core.KVStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KVSListTest {

    private static List<String> listForKVSTest() {
        List<String> list = new ArrayList<>();
        list.add("v1");
        list.add("v2");
        list.add("v3");
        return list;
    }


    @Test
    public void testPutAndGet() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1", listForKVSTest());
        Assertions.assertEquals(listForKVSTest(), store.lists().get("k1"));
    }

    @Test
    public void testDel() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1", listForKVSTest());
        Assertions.assertEquals("ok",store.lists().del("k1").toString());
        Assertions.assertNull(store.lists().get("k1"));
    }

    @Test
    public void testLPut() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1",listForKVSTest());
        List<String> list2 = new ArrayList<>();
        list2.add("v4");
        list2.add("v5");
        store.lists().lput("k1",list2);
        Assertions.assertEquals(Arrays.asList("v4","v5","v1","v2","v3"),store.lists().get("k1"));
    }

    @Test
    public void testRPut() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1",listForKVSTest());
        List<String> list2 = new ArrayList<>();
        list2.add("v4");
        list2.add("v5");
        store.lists().rput("k1", list2);
        Assertions.assertEquals(Arrays.asList("v1","v2","v3","v4","v5"),store.lists().get("k1"));
    }


    @Test
    public void testLDel() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1",listForKVSTest());
        store.lists().ldel("k1",1);
        Assertions.assertEquals(Arrays.asList("v2","v3"),store.lists().get("k1"));
        Assertions.assertEquals("key not exist",store.lists().ldel("-k",1).toString());
    }


    @Test
    public void testRDel() {
        KVStore store = new KVStoreImpl();
        store.lists().put("k1",listForKVSTest());
        store.lists().rdel("k1",1);
        Assertions.assertEquals(Arrays.asList("v1","v2"),store.lists().get("k1"));
        Assertions.assertEquals("key not exist",store.lists().rdel("-k",1).toString());
    }

}