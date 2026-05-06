package cl.fullstack.shipments.controller;

import cl.fullstack.shipments.model.Shipment;
import cl.fullstack.shipments.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "*")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }

    @GetMapping
    public ResponseEntity<?> getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShipmentById(@PathVariable java.util.UUID id) {
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }
}