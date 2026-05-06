package cl.fullstack.shipments.repository;

import cl.fullstack.shipments.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
}