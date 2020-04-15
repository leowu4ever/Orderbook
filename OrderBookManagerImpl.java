package com.cfbenchmarks.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderBookManagerImpl implements OrderBookManager {
	
	private HashMap<String, OrderBook> orderBooks= new HashMap<String, OrderBook>();
	private HashMap<String, Order> idOrderMap = new HashMap<String, Order>(); // for quick order lookup via id;
	
    public void addOrder(Order order) {
		if(isOrderValid(order)) {
			String instrument = order.getInstrument();
			if(orderBooks.get(instrument) == null) {
				OrderBook newOrderBook = new OrderBook();
				newOrderBook.addOrder(order);
				orderBooks.put(instrument, newOrderBook);
			} else {
				orderBooks.get(instrument).addOrder(order);
			}
			idOrderMap.put(order.getOrderId(), order);
		}
    }

    public void modifyOrder(String orderId, long newQuantity) {
		if(idExists(orderId)) {
			String instrument = idOrderMap.get(orderId).getInstrument();
			orderBooks.get(instrument).modify(orderId, newQuantity);
		}
    }

    public void deleteOrder(String orderId) {
		if(idExists(orderId)) {
			String instrument = idOrderMap.get(orderId).getInstrument();
			orderBooks.get(instrument).delete(orderId);
		}
    }

    public long getBestPrice(String instrument, Side side) {
		return (instrumentExists(instrument))? orderBooks.get(instrument).getBestPriceOn(side) : 0l;
    }

    public long getOrderNumAtLevel(String instrument, Side side, long price) {
		return (instrumentExists(instrument))? orderBooks.get(instrument).getOrderNumberBySideAndPrice(side, price) : 0l;
    }
 
    public long getTotalQuantityAtLevel(String instrument, Side side, long price) {
		if(instrumentExists(instrument)) {
			long totalQuantityAtLevel = orderBooks.get(instrument).getTotalQuantityBySideAndPrice(side, price);
	        return totalQuantityAtLevel;
    	} else {
    		return 0;
    	}
    }

    public long getTotalVolumeAtLevel(String instrument, Side side, long price) {
		if(instrumentExists(instrument)) {
	    	long totalVolumeAtLevel = orderBooks.get(instrument).getTotalVolumeBySideAndPrice(side, price);
	        return totalVolumeAtLevel;
    	} else {
    		return 0;
    	}
    }

    public List<Order> getOrdersAtLevel(String instrument, Side side, long price) {
		if(instrumentExists(instrument)) {
	    	List<Order> ordersAtLevel = orderBooks.get(instrument).getOrdersBySideAndPrice(side, price);
	        return ordersAtLevel;
    	} else {
    		return new ArrayList<Order>();
    	}
	}

	public boolean instrumentExists(String instrument) { 
		if (orderBooks.get(instrument) != null) return true;
		else throw new InvalidInstrumentException("Failed due to invalid instrument");
	}
	public boolean idExists(String id) { 
		if (idOrderMap.get(id) != null) return true;
		else throw new InvalidIdException("Failed due to invalid id");
	}

	public boolean isOrderValid(Order order) {
		if(order.getPrice()==0) throw new InvalidPriceException("Failed due to zero price");
		if(order.getPrice()<0) throw new InvalidPriceException("Failed due to negative price");
		if(order.getQuantity()==0) throw new InvalidQuantityException("Failed due to zero quantity");
		if(order.getQuantity()<0) throw new InvalidQuantityException("Failed due to negative quantity");
		if(idOrderMap.get(order.getOrderId()) != null) throw new InvalidIdException("Failed due to invalid id");
		return true;
	}
}
