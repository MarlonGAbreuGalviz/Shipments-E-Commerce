package com.fullstack.shipments.service.Impl;

import com.fullstack.shipments.client.OrderClient;
import com.fullstack.shipments.dto.ShipmentRequestDTO;
import com.fullstack.shipments.model.Shipment;
import com.fullstack.shipments.model.ShipmentStatus;
import com.fullstack.shipments.repository.ShipmentRepository;
import com.fullstack.shipments.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderClient orderClient;

    public ShipmentServiceImpl(
            ShipmentRepository shipmentRepository,
            OrderClient orderClient
    ) {
        this.shipmentRepository = shipmentRepository;
        this.orderClient = orderClient;
    }

    @Override
    public Shipment createShipment(ShipmentRequestDTO request) {
        shipmentRepository.findByOrderId(request.getOrderId()).ifPresent(shipment -> {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un envío para el pedido: " + request.getOrderId()
            );
        });

        Shipment shipment = Shipment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .status(ShipmentStatus.CREATED)
                .build();

        return shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(UUID shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Envío no encontrado con ID: " + shipmentId
                ));
    }

    @Override
    public Shipment getShipmentByOrderId(UUID orderId) {
        return shipmentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe envío para el pedido: " + orderId
                ));
    }

    @Override
    public List<Shipment> getShipmentsByUserId(UUID userId) {
        return shipmentRepository.findByUserId(userId);
    }

    @Override
    public Shipment updateShipmentStatus(UUID shipmentId, ShipmentStatus status) {
        Shipment shipment = getShipmentById(shipmentId);
        shipment.setStatus(status);

        Shipment updatedShipment = shipmentRepository.save(shipment);

        if (status == ShipmentStatus.DELIVERED) {
            orderClient.notifyOrderDelivered(
                    updatedShipment.getOrderId(),
                    updatedShipment.getId()
            );
        }

        return updatedShipment;
    }
}