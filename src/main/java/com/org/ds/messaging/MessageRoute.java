package com.org.ds.messaging;

import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.org.ma.utils.Constants.DELIVERY_CHANNEL;

@Component
@AllArgsConstructor
public class MessageRoute extends RouteBuilder {

    private MessageHandler handler;

    @Override
    public void configure() {
        from("kafka:%s?brokers=172.18.0.1:9092".formatted(DELIVERY_CHANNEL))
                .process(handler);
    }
}
