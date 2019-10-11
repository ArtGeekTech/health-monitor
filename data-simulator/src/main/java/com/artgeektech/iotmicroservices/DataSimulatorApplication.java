package com.artgeektech.iotmicroservices;

import com.artgeektech.iotmicroservices.model.HealthMonitorRawData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableDiscoveryClient
public class DataSimulatorApplication {

    private static final Logger logger = LoggerFactory.getLogger(DataSimulatorApplication.class);

    private static final String resourceUrl = "http://localhost:9001/ingest/healthdata";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Random random = new Random();
    private static double minVal = 10;
    private static double maxVal = 30;
    private static Timer timer = new Timer();
    private static int interval = 1000;

    public static void main(String[] args) {
        SpringApplication.run(DataSimulatorApplication.class, args);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                HealthMonitorRawData payload = new HealthMonitorRawData(genRandom(), genRandom(), (int) genRandom(), (int) genRandom());

                HttpEntity<HealthMonitorRawData> request = new HttpEntity<>(payload);

                restTemplate.postForObject(resourceUrl, request, Object.class);

                logger.info("POST to: " + resourceUrl + " with request: " + request.toString());

            }
        }, 0, interval);
    }

    private static double genRandom() {
        int randInt = random.nextInt(5);
        if (randInt == 0) {
            return 99;
        } else {
            return minVal + random.nextDouble() * (maxVal - minVal);
        }
    }
}
