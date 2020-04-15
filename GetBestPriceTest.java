package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetBestPriceTest {
    MockDataGenerator mockDataGen = null;
    OrderBookManagerImpl orderBookManager = null;

    public GetBestPriceTest() {
        mockDataGen = new MockDataGenerator();
        orderBookManager = mockDataGen.getOrderBookManagerWithRichData();
    }

    @Test
    public void testGetBestBidPrice() {
        long expectedPrice = mockDataGen.getBestBidPrice();
        long actualPrice = orderBookManager.getBestPrice(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetBestAskPrice() {
        long expectedPrice = mockDataGen.getBestAskPrice();
        long actualPrice = orderBookManager.getBestPrice(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void testGetBestPriceFromNonExistingInstrument() {
        orderBookManager.getBestPrice(mockDataGen.getNonExistingInstrument(), mockDataGen.getBidSide());
    }
}