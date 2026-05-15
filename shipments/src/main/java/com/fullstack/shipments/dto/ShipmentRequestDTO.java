package com.fullstack.shipments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ShipmentRequestDTO {

    @NotNull(message = "El ID del pedido es obligatorio")
    private UUID orderId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private UUID userId;
}