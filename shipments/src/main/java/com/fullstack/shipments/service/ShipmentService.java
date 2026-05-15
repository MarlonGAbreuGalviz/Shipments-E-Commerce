package com.fullstack.shipments.service;

import com.fullstack.shipments.dto.ShipmentRequestDTO;
import com.fullstack.shipments.model.Shipment;
import com.fullstack.shipments.model.ShipmentStatus;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {

    Shipment createShipment(ShipmentRequestDTO request);

    List<Shipment> getAllShipments();

    Shipment getShipmentById(UUID shipmentId);

    Shipment getShipmentByOrderId(UUID orderId);

    List<Shipment> getShipmentsByUserId(UUID userId);

    Shipment updateShipmentStatus(UUID shipmentId, ShipmentStatus status);
}