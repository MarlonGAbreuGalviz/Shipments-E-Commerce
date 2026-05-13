package com.fullstack.shipments.service;

import java.util.List;
import java.util.UUID;

import com.fullstack.shipments.event.OrderCreatedEvent;
import com.fullstack.shipments.model.Shipment;

public interface ShipmentService {

    Shipment createShipment(Shipment shipment);

    Shipment createShipmentFromOrder(OrderCreatedEvent event);

    List<Shipment> getAllShipments();

    Shipment getShipmentById(UUID id);
}