package com.tcs.visa.clientsOrders.dto;

import com.tcs.visa.clientsOrders.entity.Client;
import com.tcs.visa.clientsOrders.entity.Order;

public class OrderDto {
	private Long orderId;
    private Client client;
    private String description;
    private Double amount;
    
    private OrderDto() {
    	
    }
    
    public OrderDto(Long orderId, Client client, String description, Double amount) {
		super();
		this.orderId = orderId;
		this.client = client;
		this.description = description;
		this.amount = amount;
	}
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", client=" + client + ", description=" + description + ", amount="
				+ amount + "]";
	}
	
	public static OrderDto prerpareDto(Order order) {
		OrderDto orderDto = new OrderDto();
		
		orderDto.setOrderId(order.getOrderId());
		orderDto.setClient(order.getClient());
		orderDto.setDescription(order.getDescription());
		orderDto.setAmount(order.getAmount());
		
		return orderDto;
	}
}
