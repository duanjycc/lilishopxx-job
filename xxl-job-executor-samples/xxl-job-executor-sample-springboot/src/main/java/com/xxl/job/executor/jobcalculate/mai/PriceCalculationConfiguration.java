package com.xxl.job.executor.jobcalculate.mai;

import lombok.Data;


@Data
public class PriceCalculationConfiguration {
    private long  id;
    private double startValue;
    private double endValue;
    private int addSubtract;
    private double price;
}
