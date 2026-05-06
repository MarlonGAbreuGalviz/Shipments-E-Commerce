package cl.fullstack.shipments.service.Impl;

import cl.fullstack.shipments.service.ShipmentService;
import cl.fullstack.shipments.event.OrderCreatedEvent;
import cl.fullstack.shipments.model.Shipment;
import cl.fullstack.shipments.repository.ShipmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment createShipmentFromOrder(OrderCreatedEvent event) {
        Shipment shipment = Shipment.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .status("PREPARING")
                .build();

        return shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(UUID id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Envío no encontrado con ID: " + id
                ));
    }
}