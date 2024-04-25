package com.tcs.visa.clientsOrders.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long id) {
        super("Client with ID " + id + " not found");
    }
}