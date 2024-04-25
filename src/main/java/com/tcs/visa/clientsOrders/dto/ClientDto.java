package com.tcs.visa.clientsOrders.dto;

import com.tcs.visa.clientsOrders.entity.Client;

public class ClientDto {
	private Long clientId;
	private String name;
	private String email;
	
	private ClientDto() {
		
	}
	
	public ClientDto(Long clientId, String name, String email) {
		super();
		this.clientId = clientId;
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
	public void setEmail(String phoneNo) {
		this.email = phoneNo;
	}

	@Override
	public String toString() {
		return "ClientDto [clientId=" + clientId + ", name=" + name + ", email=" + email + "]";
	}

	public static ClientDto prerpareDto(Client client) {
		ClientDto clientDTO = new ClientDto();
		
		clientDTO.setClientId(client.getClientId());
		clientDTO.setName(client.getName());
		clientDTO.setEmail(client.getEmail());
		
		return clientDTO;
	}
}
