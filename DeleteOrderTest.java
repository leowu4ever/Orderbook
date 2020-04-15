package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteOrderTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

     // delete order
     @Test
     public void testDelete() {
         orderBookManager = new OrderBookManagerImpl();
         Order expectedOrder = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
         orderBookManager.addOrder(expectedOrder);
         Order actualOrder = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice()).get(0);
         long expectedOrderNumber = 1; 
         long actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
         assertEquals(expectedOrder, actualOrder);
         assertEquals(expectedOrderNumber, actualOrderNumber);
 
         orderBookManager.deleteOrder("ORDER1");
         expectedOrderNumber = 0;
         actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
         assertEquals(expectedOrderNumber, actualOrderNumber);
     }
 
    @Test(expected = InvalidIdException.class)
     public void testDeleteNonExistingId() {
         orderBookManager = new OrderBookManagerImpl();
         orderBookManager.deleteOrder(mockDataGen.getNonExistingId());
     }
}