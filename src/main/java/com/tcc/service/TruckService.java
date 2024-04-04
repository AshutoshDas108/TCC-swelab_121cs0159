package com.tcc.service;

import com.tcc.entity.Truck;

import java.util.List;

public interface TruckService {

    public boolean getCurrentStatusOfTruck(Integer truckId);

    public Truck getDetailsById(Integer TruckId);

    public List<Truck> getDetailOfAllTrucks();

    public Truck updateTruck(Integer truckId);

}
