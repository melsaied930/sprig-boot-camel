package com.example.sprigbootcamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageRouter extends RouteBuilder {
    @Autowired
    private GetTimeNow getTimeNow;
    @Autowired
    private SetTimeNow setTimeNow;

    @Override
    public void configure() throws Exception {
//        from("timer:message").transform().constant("Hi Mo").to("log:message");
//        from("timer:message").transform().constant("Time now: " + LocalDateTime.now()).to("log:message");
//        from("timer:message").bean("getTimeNow").to("log:message");
//        from("timer:message").bean(getTimeNow,"timeNow").to("log:message");
//        from("timer:message").bean(getTimeNow, "timeIs()").to("log:message");
//        from("timer:message").bean(getTimeNow, "timeNow").log("${body}").to("log:message");
//        from("timer:message").bean(getTimeNow, "timeNow").bean(setTimeNow,"timeNow").to("log:message");
        from("timer:message").bean(getTimeNow, "timeNow").process(new MyMessageProcessor()).to("log:message");
    }


}

@Component
class GetTimeNow {
    public String timeNow() {
        return "Time now is :" + LocalDateTime.now();
    }

    public String timeIs() {
        return "Time now is ::" + LocalDateTime.now();
    }
}

@Component
class SetTimeNow {
    private Logger logger = LoggerFactory.getLogger(SetTimeNow.class);

    public void timeNow(String time) {
        logger.info("SetTimeNow {}", time);
    }
}

class MyMessageProcessor implements Processor {
    private Logger logger = LoggerFactory.getLogger(MyMessageProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("MyMessageProcessor {}", exchange.getMessage().getBody());
    }
}