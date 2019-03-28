package com.artgeektech.iotmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthMonitorData implements Serializable {
    private Double temperature;
    private Double bloodPressure;
    private Integer heartBeat;
    private Integer stepCount;
    private Date timestamp;
}
