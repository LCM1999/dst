package org.dst.test.client;

import java.util.LinkedList;
import org.dst.client.DefaultDstClient;
import org.dst.client.DstClient;
import org.dst.common.entity.sortedList.SortedListEntity;
import org.dst.test.supplier.BaseTestSupplier;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SortedListProxyTest extends BaseTestSupplier {
  private static final String serverAddress = "list://127.0.0.1:8082";

  @Test
  public void testMain() {
    testPut();
    testIncItem();
    testPutItem();
    testTop();
    testDelItem();
    testTop();
  }

  private void testTop() {
    DstClient client = new DefaultDstClient(serverAddress);
    LinkedList<SortedListEntity> list = client.sortedLists().top("k1", 100);
    Assert.assertEquals(list.get(0).getMember(), "whhh");
    Assert.assertEquals(list.get(1).getMember(),"fw");
  }

  private void testDelItem() {
    DstClient client = new DefaultDstClient(serverAddress);
    client.sortedLists().delItem("k1","55");
  }

  private void testPutItem() {
    DstClient client = new DefaultDstClient(serverAddress);
    client.sortedLists().putItem("k1", new SortedListEntity("whhh",100));
  }

  private void testIncItem() {
    DstClient client = new DefaultDstClient(serverAddress);
    client.sortedLists().incrItem("k1", "fw",1);
  }

  private void testPut() {
    DstClient client = new DefaultDstClient(serverAddress);
    LinkedList<SortedListEntity> list = new LinkedList<>();
    list.add(new SortedListEntity("xswl", 9));
    list.add(new SortedListEntity("wlll", 8));
    list.add(new SortedListEntity("fw", 9));
    list.add(new SortedListEntity("55", 6));
    client.sortedLists().put("k1", list);
  }

}
