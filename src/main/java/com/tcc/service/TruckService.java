package com.tcc.service;

import com.tcc.entity.Truck;

import java.util.List;

public interface TruckService {

    public String getCurrentStatusOfTruck(Integer truckId) throws Exception;

    public Truck createTruck(Truck truck);

    public Truck getTruckById(Integer TruckId) throws Exception;

    public List<Truck> getDetailOfAllTrucks();

//    public Truck updateTruck(Integer truckId);

    public Object addConsignment(Integer truckId, Integer consId) throws Exception;

   public Truck assignTruckToBranch(Integer truckId, Integer branchId) throws Exception;

}
