package com.cfbenchmarks.interview;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetTotalVolumeTest {
    MockDataGenerator mockDataGen = new MockDataGenerator();
    OrderBookManagerImpl orderBookManager = null;

    public GetTotalVolumeTest() {
        mockDataGen = new MockDataGenerator();
        orderBookManager = mockDataGen.getOrderBookManagerWithRichData();
    }    

    @Test
    public void testGetBidTotalVolumeAtLevel() {
        long expectedTotalVolume = mockDataGen.getBidTotalVolume(mockDataGen.getExistingPrice());
        long actualTotalVolume = orderBookManager.getTotalVolumeAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedTotalVolume, actualTotalVolume);
    }

    @Test 
    public void testGetAskTotalVolumneAtLevel() {
        long expectedTotalVolume = mockDataGen.getAskTotalVolume(mockDataGen.getExistingPrice());
        long actualTotalVolume = orderBookManager.getTotalVolumeAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getExistingPrice());
        assertEquals(expectedTotalVolume, actualTotalVolume);
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetBidTotalVolumeAtNonExistingLevel() {
        orderBookManager.getTotalVolumeAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidPriceException.class)
    public void testGetAskTotalVolumeAtNonExistingLevel() {
        orderBookManager.getTotalVolumeAtLevel(mockDataGen.getExistingInstrument(), mockDataGen.getAskSide(), mockDataGen.getNonExistingPrice());
    }

    @Test(expected = InvalidInstrumentException.class)
    public void testGetTotalVolumeAtLevelFromNonExistingInstrument() {
        orderBookManager.getTotalVolumeAtLevel(mockDataGen.getNonExistingInstrument(), mockDataGen.getBidSide(), mockDataGen.getExistingPrice());
    }
}