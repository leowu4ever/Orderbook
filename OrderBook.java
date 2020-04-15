package com.cfbenchmarks.interview;

import java.util.List;

public class OrderBook {
	
	private PriceContainer bids = new PriceContainer();
	private PriceContainer asks = new PriceContainer();
	
	public OrderBook() {}

	public void addOrder(Order order) {
		if(order.getSide() == Side.buy) bids.add(order);
		if(order.getSide() == Side.sell) asks.add(order);
	}

	public void modify(String orderId, long newQuantity) {
		if(bids.contains(orderId)) bids.modify(orderId, newQuantity);
		if(asks.contains(orderId)) asks.modify(orderId, newQuantity);
	}

	public void delete(String orderId) {
		if(bids.contains(orderId)) bids.delete(orderId);
		if(asks.contains(orderId)) asks.delete(orderId);
 	}

	public long getBestPriceOn(Side side) {
		return (side == Side.buy)? bids.getHighestPrice() : asks.getLowestPrice();
	}

	public long getOrderNumberBySideAndPrice(Side side, long price) {
		return (side == Side.buy)? bids.getOrderNumberByPrice(price) : asks.getOrderNumberByPrice(price);
	}

	public long getTotalQuantityBySideAndPrice(Side side, long price) {
		return (side == Side.buy)? bids.getTotalQuantityByPrice(price) : asks.getTotalQuantityByPrice(price);
	}

	public long getTotalVolumeBySideAndPrice(Side side, long price) {
		return (side == Side.buy)? bids.getTotalVolumeByPrice(price) : asks.getTotalVolumeByPrice(price);
	}

	public List<Order> getOrdersBySideAndPrice(Side side, long price) {
		return (side == Side.buy)? bids.getOrdersByPrice(price) : asks.getOrdersByPrice(price);
	}
}
