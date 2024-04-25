package com.tcs.visa.clientsOrders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.visa.clientsOrders.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}