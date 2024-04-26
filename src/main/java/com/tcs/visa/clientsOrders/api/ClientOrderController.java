package com.tcs.visa.clientsOrders.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.visa.clientsOrders.dto.ClientDto;
import com.tcs.visa.clientsOrders.dto.OrderDto;
import com.tcs.visa.clientsOrders.entity.Client;
import com.tcs.visa.clientsOrders.entity.Order;
import com.tcs.visa.clientsOrders.exception.ClientNotFoundException;
import com.tcs.visa.clientsOrders.repository.ClientRepository;
import com.tcs.visa.clientsOrders.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1")
public class ClientOrderController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/clients")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        Client client = new Client(clientDto.getName(), clientDto.getEmail());
        Client savedClient = clientRepository.save(client);
        ClientDto savedClientDTO = new ClientDto(savedClient.getClientId(), savedClient.getName(), savedClient.getEmail());
        return ResponseEntity.ok(savedClientDTO);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam(name = "mailDomain", required = false) String mailDomain)  {
        List<Client> clients = clientRepository.findAll();
        List<ClientDto> clientDTOs = new ArrayList<>();
        for (Client client : clients) {
            ClientDto clientDTO = new ClientDto(client.getClientId(), client.getName(), client.getEmail());
            clientDTOs.add(clientDTO);
        }
        
        //Stream to filter all employees by mail domain
        if (!(mailDomain == null)) {
        	clientDTOs = clientDTOs.stream()
        			.filter(t -> t.getEmail().toLowerCase().endsWith( "@" + mailDomain + ".com"))
        			.collect(Collectors.toList());
		}
        
        return ResponseEntity.ok(clientDTOs);
    }
    
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
    	Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        clientRepository.delete(clientOptional.get());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            OrderDto OrderDto = new OrderDto(order.getOrderId(), order.getClient(), order.getDescription(), order.getAmount());
            orderDTOs.add(OrderDto);
        }
        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

        Client client = clientRepository.findById(orderDto.getClient().getClientId())
                .orElseThrow(() -> new ClientNotFoundException(orderDto.getClient().getClientId()));

        Order order = new Order(client, orderDto.getDescription(), orderDto.getAmount());
        Order savedOrder = orderRepository.save(order);

        ClientDto clientDto = new ClientDto(savedOrder.getClient().getClientId(), savedOrder.getClient().getName(), savedOrder.getClient().getEmail());
        OrderDto savedOrderDTO = new OrderDto(savedOrder.getOrderId(), client, savedOrder.getDescription(), savedOrder.getAmount());

        return ResponseEntity.ok(savedOrderDTO);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        orderRepository.delete(orderOptional.get());
        return ResponseEntity.noContent().build();
    }
    
  //Endpoint to retrieve all orders for a specific client
    @GetMapping("/{clientId}/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByClient(@PathVariable Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Order> orders = orderRepository.findByClient(clientOptional.get());
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDTO = new OrderDto(order.getOrderId(), order.getClient(), order.getDescription(), order.getAmount());
            orderDtos.add(orderDTO);
        }
        return ResponseEntity.ok(orderDtos);
    }
}

