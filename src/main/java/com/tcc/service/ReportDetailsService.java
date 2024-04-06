package com.tcc.service;

import java.time.LocalDateTime;

public interface ReportDetailsService {
    public LocalDateTime idleTimeOfTuck(Integer truckId);
    public LocalDateTime waitingTimeOfConsignment (Integer consId);
    public Float calculateTotalReveneuGenerated(Integer truckId, Integer consId);
}
