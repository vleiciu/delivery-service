package com.org.ds.messaging;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.org.ma.utils.Constants.DELIVERY_CHANNEL;

@Component
@RequiredArgsConstructor
public class MessageRoute extends RouteBuilder {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String brokerAddress;

    private MessageHandler handler;

    @Override
    public void configure() {
        from("kafka:%s?brokers=%s".formatted(DELIVERY_CHANNEL, brokerAddress))
                .process(handler);
    }
}
