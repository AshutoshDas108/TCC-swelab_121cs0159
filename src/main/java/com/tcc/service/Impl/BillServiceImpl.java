package com.tcc.service.Impl;

import com.tcc.service.BillService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BillServiceImpl implements BillService {
    @Override
    public LocalDateTime idleTimeOfTuck(Integer truckId) {
        return null;
    }

    @Override
    public LocalDateTime waitingTimeOfConsignment(Integer consId) {
        return null;
    }

    @Override
    public Float calculateTotalReveneuGenerated(Integer truckId, Integer consId) {
        return null;
    }
}
