package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddOrderTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;
    
    @Test
    public void testAddOrder() {
        orderBookManager = new OrderBookManagerImpl();
        Order expectedOrder = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(expectedOrder);
        Order actualOrder = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice()).get(0);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test(expected = InvalidPriceException.class)
    public void testAddOrderWithNegativePrice() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getNegativePrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
    }

    @Test(expected = InvalidPriceException.class)
    public void testAddOrderWithZeroPrice() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getZeroPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
    }

    @Test(expected = InvalidQuantityException.class)
    public void testAddOrderWithNegativeQuantity() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getNegativeQuantity());
        orderBookManager.addOrder(order);
    }

    @Test(expected = InvalidQuantityException.class)
    public void testAddOrderWithZeroQuantity() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getZeroQuantity());
        orderBookManager.addOrder(order);
    }

    @Test(expected = InvalidIdException.class)
    public void testAddOrderWithExistingId() {
        orderBookManager = new OrderBookManagerImpl();
        Order order1 = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        Order order2 = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order1);
        orderBookManager.addOrder(order2);
    }
}
