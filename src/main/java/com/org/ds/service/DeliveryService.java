package com.org.ds.service;


import com.org.ds.entity.DeliveryInfo;
import com.org.ds.enums.DeliveryStatus;
import com.org.ds.repository.DeliveryRepository;
import com.org.ma.enums.MessageType;
import com.org.ma.enums.Subject;
import com.org.ma.model.Delivery;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.org.ma.enums.Header.RESPONSE;
import static com.org.ma.utils.Constants.*;
import static com.org.ma.utils.Constants.MESSAGE_TYPE;

@Service
@AllArgsConstructor
public class DeliveryService {

    private KafkaProducer<String, Delivery> producer;

    private DeliveryRepository repository;

    public Optional<DeliveryInfo> getNextDelivery() {
        return repository.findFirstByDeliveryStatus(DeliveryStatus.PENDING);
    }

    public Optional<DeliveryInfo> getInProgressDelivery() {
        return repository.findFirstByDeliveryStatus(DeliveryStatus.IN_PROGRESS);
    }

    public void resolveDelivery(DeliveryInfo deliveryInfo) {
        Delivery delivery = Delivery.builder()
                .submittedAt(deliveryInfo.getSubmittedAt())
                .deliveryTime(deliveryInfo.getDeliveryTime())
                .cost(deliveryInfo.getCost())
                .address(deliveryInfo.getAddress())
                .correlationId(deliveryInfo.getCorrelationId())
                .deliveredAt(deliveryInfo.getDeliveredAt())
                .orderId(deliveryInfo.getOrderId())
                .restaurantId(deliveryInfo.getRestaurantId())
                .courierId(deliveryInfo.getCourier().getCourierId())
                .build();

        ProducerRecord<String, Delivery> record = new ProducerRecord<>(ORDER_CHANNEL, MESSAGE, delivery);
        record.headers().add(SUBJECT, "%s_%s".formatted(Subject.DELIVERY.name(), RESPONSE.name()).getBytes());
        record.headers().add(MESSAGE_TYPE, MessageType.REGULAR.name().getBytes());
        producer.send(record);


    }

    public void saveDelivery(DeliveryInfo deliveryInfo) {
        repository.save(deliveryInfo);
    }
}
