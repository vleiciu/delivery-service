package com.org.ds.messaging;


import com.org.ds.entity.DeliveryInfo;
import com.org.ds.enums.DeliveryStatus;
import com.org.ds.service.DeliveryService;
import com.org.ma.model.Delivery;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageHandler implements Processor {

    private DeliveryService deliveryService;

    @Override
    public void process(Exchange exchange) {
        Delivery delivery = exchange.getMessage(Delivery.class);
        deliveryService.saveDelivery(DeliveryInfo.builder()
                .cost(delivery.getCost())
                .address(delivery.getAddress())
                .correlationId(delivery.getCorrelationId())
                .orderId(delivery.getOrderId())
                .deliveryTime(delivery.getDeliveryTime())
                .restaurantId(delivery.getRestaurantId())
                .submittedAt(delivery.getSubmittedAt())
                .deliveryStatus(DeliveryStatus.PENDING)
                .build());
    }
}
