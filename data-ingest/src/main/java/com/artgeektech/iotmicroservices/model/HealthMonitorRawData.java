package com.artgeektech.iotmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthMonitorRawData implements Serializable {
    @NotNull
    @Min(1)
    @Max(100)
    private Double temperature;

    @NotNull
    @Min(1)
    @Max(100)
    private Double bloodPressure;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer heartBeat;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer stepCount;
}
