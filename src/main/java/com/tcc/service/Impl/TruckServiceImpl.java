package com.tcc.service.Impl;

import com.tcc.entity.Truck;
import com.tcc.service.TruckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {
    @Override
    public boolean getCurrentStatusOfTruck(Integer truckId) {
        return false;
    }

    @Override
    public Truck getDetailsById(Integer TruckId) {
        return null;
    }

    @Override
    public List<Truck> getDetailOfAllTrucks() {
        return null;
    }

    @Override
    public Truck updateTruck(Integer truckId) {
        return null;
    }
}
