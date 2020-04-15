package com.cfbenchmarks.interview;

import java.util.LinkedList;
import java.util.List;

public class OrderList {
	
	private long orderNumber = 0;
	private long totalQuantity = 0;
	private long totalVolume = 0 ;
	private List<Order> orders = new LinkedList<Order>();

	public void add(Order order) {
		orders.add(order);
		orderNumber++;
		totalQuantity += order.getQuantity();
		totalVolume += order.getPrice() * order.getQuantity();
	}
	
	// Remoain the same position if the quantity decreases 
	// otherwise move it to the end of the order list
	public void modifyQuantityById(String orderId, long newQuantity) {
		Order order;
		for(int i = 0; i<orders.size(); i++) {
			if(orders.get(i).getOrderId().equals(orderId)) {
				order = orders.get(i);
				long oldQuantity = order.getQuantity();
				totalQuantity -= oldQuantity;
				totalQuantity += newQuantity;
				totalVolume -= order.getPrice() * oldQuantity;
				totalVolume += order.getPrice() * newQuantity;

				if(newQuantity > oldQuantity) {
					orders.remove(i);
					order.setQuantity(newQuantity);
					orders.add(order);
				} else {
					orders.get(i).setQuantity(newQuantity);
				}
				break;
			}
		}
	}

	public void deleteById(String orderId) {
		Order order;
		for(int i = 0; i<orders.size(); i++) {
			order = orders.get(i);
			if(order.getOrderId().equals(orderId)) {
				orderNumber--;
				totalQuantity -= order.getQuantity();
				totalVolume -= order.getPrice() * order.getQuantity();
				orders.remove(i);
			}
		}
	}

	public long getOrderNumber() { return orderNumber; }
	public long getTotalQuantity() { return totalQuantity; }
	public long getTotalVolume() { return totalVolume; }
	public List<Order> getOrders() { return orders; } 
}
