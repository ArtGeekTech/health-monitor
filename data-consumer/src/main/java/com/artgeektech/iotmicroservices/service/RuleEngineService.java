package com.artgeektech.iotmicroservices.service;

import com.artgeektech.iotmicroservices.model.HealthMonitorData;
import org.springframework.stereotype.Component;

/**
 * Created by guang on 7:16 PM 8/21/18.
 */
@Component
public class RuleEngineService {

    public void applyRules(HealthMonitorData data) {

        if (data.getTemperature() > 50) {
            triggerActionAlert("Temperature too high, pls cool down your body!!!");
        }
        if (data.getStepCount() < 100) {
            triggerActionAlert("StepCount too little, get up and work out!!!");
        }
        if (data.getHeartBeat() > 50) {
            triggerActionAlert("HeartBeat too high, pls see doctor!!!");
        }
    }

    private void triggerActionAlert(String msg) {
        System.out.println("\n\nAction Triggered! \n!!!!!Sending the Email Alert: " + msg + "\n\n");
    }
}
