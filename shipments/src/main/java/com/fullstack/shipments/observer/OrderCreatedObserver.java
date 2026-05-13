package com.fullstack.shipments.observer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fullstack.shipments.event.OrderCreatedEvent;
import com.fullstack.shipments.service.ShipmentService;

@RestController
@RequestMapping("/api/shipments/observer")
@CrossOrigin(origins = "*")
public class OrderCreatedObserver {

    private final ShipmentService shipmentService;

    public OrderCreatedObserver(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/order-created")
    public ResponseEntity<Void> handleOrderCreated(@RequestBody OrderCreatedEvent event) {
        System.out.println("SHIPMENTS OBSERVER: Evento recibido desde Orders");
        System.out.println("SHIPMENTS OBSERVER: Pedido recibido: " + event.getOrderId());
        System.out.println("SHIPMENTS OBSERVER: Creando envío automático...");

        shipmentService.createShipmentFromOrder(event);

        System.out.println("SHIPMENTS OBSERVER: Envío creado correctamente");

        return ResponseEntity.ok().build();
    }
}
