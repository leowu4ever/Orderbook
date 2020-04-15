package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetOrderNumberTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

    public GetOrderNumberTest() {
        mockDataGen = new MockDataGenerator();
        orderBookManager = mockDataGen.getOrderBookManagerWithRichData();
    }

    @Test
    public void testGetBidOrderNumberAtLevel() {
        long expectedOrderNumber = mockDataGen.getBidOrderNumber();
        long actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedOrderNumber, actualOrderNumber);
    }

    @Test
    public void testGetAskOrderNumberAtLevel() {
        long expectedOrderNumber = mockDataGen.getAskOrderNumber();
        long actualOrderNumber = orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedOrderNumber, actualOrderNumber);
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetBidOrderNumberAtNonExistingLevel() {
        orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetAskOrderNumberAtNonExistingLevel() {
        orderBookManager.getOrderNumAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidInstrumentException.class)
    public void testGetOrderNumberAtLevelFromNonExistingInstrument() {
        orderBookManager.getOrderNumAtLevel(mockDataGen.getNonExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
    }
}