package com.cfbenchmarks.interview;

import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class PriceContainer {
	
	private HashMap<String, Long> idPriceMap = new HashMap<String, Long>();
	private TreeMap<Long, OrderList> priceOrderListMap = new TreeMap<Long, OrderList>();
	
	public void add(Order order) {
		long price = order.getPrice();
		String id = order.getOrderId();
		idPriceMap.put(id, price);
		OrderList orders;
		if(priceOrderListMap.get(price) == null) {
			orders = new OrderList();
		} else {
			orders = priceOrderListMap.get(price);
		}
		orders.add(order);
		priceOrderListMap.put(price,orders);
	}

	public boolean contains(String orderId) {
		Set<String> idsExist = idPriceMap.keySet();
		return idsExist.contains(orderId)? true : false;
	}

	public void modify(String orderId, long newQuantity) {
		if(isQuantityValid(newQuantity)) {
			long price = idPriceMap.get(orderId);
			priceOrderListMap.get(price).modifyQuantityById(orderId, newQuantity);
		}
	}
		
	public void delete(String orderId) {
		long price = idPriceMap.get(orderId);
		priceOrderListMap.get(price).deleteById(orderId);
	}
		
	public long getHighestPrice() {
		return priceOrderListMap.lastKey() == null? 0 : priceOrderListMap.lastKey();
	}

	public long getLowestPrice() {
		return priceOrderListMap.firstKey() == null? 0 : priceOrderListMap.firstKey();
	}
	
	public long getOrderNumberByPrice(long price) {
		return isPriceValid(price)? priceOrderListMap.get(price).getOrderNumber():0;
	}
	
	public long getTotalQuantityByPrice(long price) {
		return isPriceValid(price)? priceOrderListMap.get(price).getTotalQuantity():0;
	}
	
	public long getTotalVolumeByPrice(long price) {
		return isPriceValid(price)? priceOrderListMap.get(price).getTotalVolume():0;
	}
	
	public List<Order> getOrdersByPrice(long price) {
		List<Order> emptyOrders = new LinkedList<Order>();
		return isPriceValid(price)? priceOrderListMap.get(price).getOrders():emptyOrders;
	}

	public boolean isPriceValid(long price) { 
		if(price>0 && priceOrderListMap.get(price)!=null) return true;
		else throw new InvalidPriceException("Failed due to invalid price");
	}

	public boolean isQuantityValid(long quantity) {
		if(quantity==0) throw new InvalidQuantityException("Failed due to zero quantity");
		if(quantity<0) throw new InvalidQuantityException("Failed due to negative quantity");
		return true;
	}
}

