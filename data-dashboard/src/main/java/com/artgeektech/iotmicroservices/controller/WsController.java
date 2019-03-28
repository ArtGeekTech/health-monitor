package com.artgeektech.iotmicroservices.controller;

import com.artgeektech.iotmicroservices.Constants;
import com.artgeektech.iotmicroservices.model.HealthMonitorData;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;

/**
 * Created by guang on 1:30 PM 8/26/18.
 */
@Controller
public class WsController {

    private static final Logger logger = LoggerFactory.getLogger(WsController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @RabbitListener(queues = Constants.QUEUE_NAME)  // Subscribe to the Message Queue
    public void sendRealTimeData(HealthMonitorData healthData) {
        logger.info("Received message from MQ '{}'", healthData);
        messagingTemplate.convertAndSend("/topic/realtime", healthData.toString());
    }

//    @MessageMapping("/message")
//    @SendTo("/topic/mural")
//    public String send1(String msg) throws Exception {
//        return msg + "zzzzz";
//    }
//
//    //    @Scheduled(fixedRate = 1000)
//    public void callback() {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date()));
//        messagingTemplate.convertAndSend("/topic/mural", df.format(new Date()));
//    }
//
//    @GetMapping("/get/{msg}")
//    @ResponseBody
//    public String get(@PathVariable String msg) {
//        return msg;
//    }
//
//    @GetMapping("/get2/{msg}")
//    @ResponseBody
//    public String get2(@PathVariable String msg) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("get2 " + msg + df.format(new Date()));
//        messagingTemplate.convertAndSend("/topic/mural", "get2 " + msg + df.format(new Date()));
//        return msg + "get2";
//    }
}
