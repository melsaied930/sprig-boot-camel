package com.example.sprigbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:active-mq-queue")
                .to("log:active-mq-receiver");
    }
}