package com.fullstack.shipments.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;

    @Value("${orders.base-url}")
    private String ordersBaseUrl;

    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyOrderDelivered(UUID orderId, UUID shipmentId) {
        String url = ordersBaseUrl + "/events/order-delivered";

        OrderDeliveredEvent event = new OrderDeliveredEvent(
                orderId,
                shipmentId,
                "DELIVERED"
        );

        restTemplate.postForObject(url, event, Void.class);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDeliveredEvent {
        private UUID orderId;
        private UUID shipmentId;
        private String status;
    }
}