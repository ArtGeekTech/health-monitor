package com.artgeektech.iotmicroservices.controller;

import com.artgeektech.iotmicroservices.Constants;
import com.artgeektech.iotmicroservices.model.HealthMonitorData;
import com.artgeektech.iotmicroservices.model.HealthMonitorRawData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;


@RestController
public class DataIngestController {

    private static final Logger logger = LoggerFactory.getLogger(DataIngestController.class);

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;


//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Exchange exchange;





    @PostMapping("/healthdata/ingest")  // validate payload from request body
    public HealthMonitorData ingest(@Valid @RequestBody HealthMonitorRawData rawData) {
        // preprocess
        HealthMonitorData healthData = preprocess(rawData);

        // publish to MQ
        rabbitTemplate.convertAndSend(exchange.getName(), Constants.ROUTING_KEY_HISTORY, healthData);

        rabbitTemplate.convertAndSend(exchange.getName(), Constants.ROUTING_KEY_REALTIME, healthData);

        logger.info("ingested data: " + healthData.toString());
        return healthData;
    }



    private HealthMonitorData preprocess(HealthMonitorRawData rawData) {
        HealthMonitorData healthData = new HealthMonitorData();

        // add more info from system
        healthData.setTimestamp(new Date());
//        airData.setSensorId()...

        // standardize data format
        healthData.setBloodPressure(Math.round(rawData.getBloodPressure() * 100.0) / 100.0);
        healthData.setTemperature(Math.round(rawData.getTemperature() * 100.0) / 100.0);
        healthData.setStepCount(rawData.getStepCount());
        healthData.setHeartBeat(rawData.getHeartBeat());

        return healthData;
    }
}
