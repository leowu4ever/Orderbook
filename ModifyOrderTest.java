package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class ModifyOrderTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

    @Test
    public void testModifyOrderWithIncreasedQuantity() {
        orderBookManager = new OrderBookManagerImpl();
        Order order1 = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        Order order2 = new Order("ORDER2", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order1);
        orderBookManager.addOrder(order2);
        List<Order> orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(order1, orders.get(0));
        assertEquals(order2, orders.get(1));

        long newQuantity = mockDataGen.getExsitingQuantity()+1l;
        orderBookManager.modifyOrder("ORDER1", newQuantity);
        orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        Order orderWithIncreasedQuantity = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), newQuantity);
        assertEquals(order2, orders.get(0));
        assertEquals(orderWithIncreasedQuantity, orders.get(1));
    }

    @Test
    public void testModifyOrderWithDecreasedQuantity() {
        orderBookManager = new OrderBookManagerImpl();
        Order order1 = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        Order order2 = new Order("ORDER2", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order1);
        orderBookManager.addOrder(order2);
        List<Order> orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(order1, orders.get(0));
        assertEquals(order2, orders.get(1));

        long newQuantity = mockDataGen.getExsitingQuantity()-1l;
        orderBookManager.modifyOrder("ORDER1", newQuantity);
        orders = orderBookManager.getOrdersAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        Order orderWithIncreasedQuantity = new Order("ORDER1", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), newQuantity);
        assertEquals(orderWithIncreasedQuantity, orders.get(0));
        assertEquals(order2, orders.get(1));
    }

    @Test(expected = InvalidQuantityException.class)
    public void testModifyOrderWithNegativeQuantity() {
        orderBookManager = new OrderBookManagerImpl();
        Order order = new Order("ORDER", mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice(), mockDataGen.getExsitingQuantity());
        orderBookManager.addOrder(order);
        long newQuantity = -1l;
        orderBookManager.modifyOrder("ORDER", newQuantity);
    }

    @Test(expected = InvalidIdException.class)
    public void testModifyOrderWithNonExistingId() {
        orderBookManager = new OrderBookManagerImpl();
        orderBookManager.modifyOrder(mockDataGen.getNonExistingId(), mockDataGen.getExsitingQuantity());
    }
}