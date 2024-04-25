package com.tcs.visa.clientsOrders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	private String name;
	private String email;
	
	protected Client() {
		
	}
	
	public Client(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
