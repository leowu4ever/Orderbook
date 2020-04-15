package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GetOrdersTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

    public GetOrdersTest() {
        mockDataGen = new MockDataGenerator();
    }
    // get list of orders
    @Test 
    public void testGetBidOrdersAtLevel() {
        orderBookManager = new OrderBookManagerImpl();
        Order expectedOrder1 = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        Order expectedOrder2 = new Order("ORDER2", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(expectedOrder1);
        orderBookManager.addOrder(expectedOrder2);

        long expectedOrderNumber = 2;
        long actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        List<Order> orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedOrder1, orders.get(0));
        assertEquals(expectedOrder2, orders.get(1));
        assertEquals(expectedOrderNumber, actualOrderNumber); 
    }

    @Test
    public void testGetAskOrdersAtLevel() {
        orderBookManager = new OrderBookManagerImpl();
        Order expectedOrder1 = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        Order expectedOrder2 = new Order("ORDER2", mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(expectedOrder1);
        orderBookManager.addOrder(expectedOrder2);

        long expectedOrderNumber = 2;
        long actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
        List<Order> orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedOrder1, orders.get(0));
        assertEquals(expectedOrder2, orders.get(1));
        assertEquals(expectedOrderNumber, actualOrderNumber); 
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetBidOrdersAtNonExistingLevel() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
        orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetAskOrdersAtNonExistingLevel() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
        orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getNonExistingPrice());
    }

    @Test
    public void testGetOrdersFromNonExistingInstrument() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER1", mockDataGen.getNonExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
        orderBookManager.getOrderNumAtLevel(mockDataGen.getNonExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
    }
}