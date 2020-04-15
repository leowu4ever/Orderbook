package com.cfbenchmarks.interview;

public class MockDataGenerator {

    OrderBookManagerImpl orderBookManager = null;
    private String[] instruments = {"Bitcoin", "Ethereum"};
    private Side[] sides = {Side.buy, Side.sell};
    private long[] prices = {10000l, 20000l};
    private long[] quantities = {10l,20l};
    private final String nonExistingInstrument = "NON_Existing_INSTRUMENT";
    private final long nonExistingPrice = 2l;
    private final String nonExistingId = "NON_Existing_ID";
    private final long negativePrice = -2l;
    private final long zeroPrice = 0l;
    private final long negativeQuantity = -3l;
    private final long zeroQuantity = 0l;

    public String getNonExistingInstrument() { return nonExistingInstrument; }
    public long getNonExistingPrice() { return nonExistingPrice; }
    public String getNonExistingId() { return nonExistingId; }
    public long getNegativePrice() { return negativePrice; }
    public long getZeroPrice() { return zeroPrice; }
    public long getNegativeQuantity() { return negativeQuantity; }
    public long getZeroQuantity() { return zeroQuantity; }

    public String getExistingInstrument() { return instruments[0]; }
    public long getExistingPrice() { return prices[0]; }
    public long getExsitingQuantity() { return quantities[0]; }
    
    public long getBestBidPrice() { return prices[1]; }
    public long getBestAskPrice() { return prices[0]; }
    public Side getBidSide() { return Side.buy; }
    public Side getAskSide() { return Side.sell; }
    public long getBidOrderNumber() { return quantities.length; }
    public long getAskOrderNumber() { return quantities.length; }

    public long getBidTotalVolume(long price) {
        long totalVolume = 0;
        for(long quantity : quantities) totalVolume += price * quantity;
        return totalVolume;
    }

    public long getAskTotalVolume(long price) {
        long totalVolume = 0;
        for(long quantity : quantities) totalVolume += price * quantity;
        return totalVolume;    
    }
    
    public long getBidTotalQuantity() {
        long totalQuantity = 0;
        for (long quantity : quantities)  totalQuantity += quantity; 
        return totalQuantity;
    }
    
    public long getAskTotalQuantity() {
        long totalQuantity = 0;
        for (long quantity : quantities)  totalQuantity += quantity; 
        return totalQuantity;
    }
    
    public OrderBookManagerImpl getOrderBookManagerWithRichData() {
        orderBookManager = new OrderBookManagerImpl();
        Order order;
        String orderId;
        for(int i = 0; i<instruments.length; i++) {
            for(int j = 0; j<sides.length; j++) {
                for(int k = 0; k<prices.length; k++) {
                    for(int l = 0; l<quantities.length; l++) {

                        if((sides[j] == Side.buy)) {
                            orderId = instruments[i]+"buy" + Long.toString(prices[k]) + Long.toString(quantities[l]);
                        } else {
                            orderId = instruments[i]+"sell" + Long.toString(prices[k]) + Long.toString(quantities[l]);
                        }
                        order = new Order(orderId, instruments[i], sides[j], prices[k], quantities[l]);
                        orderBookManager.addOrder(order);
                    }
                }
            }
        }
        return orderBookManager;
    }
}