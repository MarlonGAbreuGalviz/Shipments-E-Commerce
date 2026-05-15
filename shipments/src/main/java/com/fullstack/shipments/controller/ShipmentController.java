package com.fullstack.shipments.controller;

import com.fullstack.shipments.dto.ShipmentRequestDTO;
import com.fullstack.shipments.dto.ShipmentResponseDTO;
import com.fullstack.shipments.model.Shipment;
import com.fullstack.shipments.model.ShipmentStatus;
import com.fullstack.shipments.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<ShipmentResponseDTO> createShipment(
            @Valid @RequestBody ShipmentRequestDTO request
    ) {
        Shipment shipment = shipmentService.createShipment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ShipmentResponseDTO.fromEntity(shipment));
    }

    @GetMapping
    public ResponseEntity<?> getAllShipments() {
        return ResponseEntity.ok(
                shipmentService.getAllShipments()
                        .stream()
                        .map(ShipmentResponseDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentById(
            @PathVariable UUID shipmentId
    ) {
        Shipment shipment = shipmentService.getShipmentById(shipmentId);
        return ResponseEntity.ok(ShipmentResponseDTO.fromEntity(shipment));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentByOrderId(
            @PathVariable UUID orderId
    ) {
        Shipment shipment = shipmentService.getShipmentByOrderId(orderId);
        return ResponseEntity.ok(ShipmentResponseDTO.fromEntity(shipment));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getShipmentsByUserId(
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
                shipmentService.getShipmentsByUserId(userId)
                        .stream()
                        .map(ShipmentResponseDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("/{shipmentId}/status")
    public ResponseEntity<ShipmentResponseDTO> updateShipmentStatus(
            @PathVariable UUID shipmentId,
            @RequestParam ShipmentStatus status
    ) {
        Shipment shipment = shipmentService.updateShipmentStatus(shipmentId, status);
        return ResponseEntity.ok(ShipmentResponseDTO.fromEntity(shipment));
    }
}