package com.tcs.visa.clientsOrders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.visa.clientsOrders.entity.Client;
import com.tcs.visa.clientsOrders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByClient(Client client);
}
