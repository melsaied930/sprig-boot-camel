package com.example.sprigbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:active-mq-sender?period=10000")
                .transform()
                .constant("Message to Active MQ")
                .log("${body}")
                .to("activemq:active-mq-queue");
    }
}