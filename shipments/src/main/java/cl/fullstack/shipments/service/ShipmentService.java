package cl.fullstack.shipments.service;

import cl.fullstack.shipments.event.OrderCreatedEvent;
import cl.fullstack.shipments.model.Shipment;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {

    Shipment createShipment(Shipment shipment);

    Shipment createShipmentFromOrder(OrderCreatedEvent event);

    List<Shipment> getAllShipments();

    Shipment getShipmentById(UUID id);
}