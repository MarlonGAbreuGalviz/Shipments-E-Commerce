package com.fullstack.shipments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.shipments.model.Shipment;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
}