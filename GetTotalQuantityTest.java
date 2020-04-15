package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetTotalQuantityTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

    public GetTotalQuantityTest() {
        mockDataGen = new MockDataGenerator();
        orderBookManager = mockDataGen.getOrderBookManagerWithRichData();
    }

    @Test
    public void testGetBidTotalQuantityAtLevel() {
        long expectedTotalQuantity = mockDataGen.getBidTotalQuantity();
        long actualTotalQuantity = orderBookManager.getTotalQuantityAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedTotalQuantity, actualTotalQuantity);
    }

    @Test
    public void testGetAskTotalQuantityAtLevel() {
        long expectedTotalQuantity = mockDataGen.getAskTotalQuantity();
        long actualTotalQuantity = orderBookManager.getTotalQuantityAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedTotalQuantity, actualTotalQuantity);
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetBidTotalQuantityAtNonExistingLevel() {
        orderBookManager.getTotalQuantityAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetAskTotalQuantityAtNonExistingLevel() {
        orderBookManager.getTotalQuantityAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidInstrumentException.class)
    public void testGetTotalQuantityAtLevelFromNonExistingInstrument() {
        orderBookManager.getTotalQuantityAtLevel(mockDataGen.getNonExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
    }
}