package com.artgeektech.iotmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "healthdata")
public class HealthMonitorData implements Serializable {
    private Double temperature;
    private Double bloodPressure;
    private Integer heartBeat;
    private Integer stepCount;
    private Date timestamp;
}