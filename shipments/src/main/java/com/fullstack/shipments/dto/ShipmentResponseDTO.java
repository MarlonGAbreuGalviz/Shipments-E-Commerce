package com.fullstack.shipments.dto;

import com.fullstack.shipments.model.Shipment;
import com.fullstack.shipments.model.ShipmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ShipmentResponseDTO {

    private UUID id;
    private UUID orderId;
    private UUID userId;
    private ShipmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ShipmentResponseDTO fromEntity(Shipment shipment) {
        return ShipmentResponseDTO.builder()
                .id(shipment.getId())
                .orderId(shipment.getOrderId())
                .userId(shipment.getUserId())
                .status(shipment.getStatus())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }
}