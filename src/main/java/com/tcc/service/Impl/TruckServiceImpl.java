package com.tcc.service.Impl;

import com.tcc.entity.BranchOffice;
import com.tcc.entity.Consignment;
import com.tcc.entity.Truck;
import com.tcc.repository.BranchOfficeRepository;
import com.tcc.repository.ConsignmentRepository;
import com.tcc.repository.TruckRepository;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;


    @Override
    public String getCurrentStatusOfTruck(Integer truckId) throws Exception {
        Optional<Truck> truckOptional = truckRepository.findById(truckId);
        if(truckOptional.isEmpty()){
            throw new Exception("Truck NOT FOUND WITH ID :" + truckId);
        }
        Truck truck = truckOptional.get();
        Float cap = truck.getCapacity();
        Float consVol = truck.getConsignmentVolume();
        if(cap > consVol){
            return "NOT FULL can accommodate consignment of vol : " + (cap-consVol);
        }
        else{
            return  "TRUCK IS FULL";
        }
    }

    @Override
    public Truck createTruck(Truck truck) {

        Truck createdTruck = new Truck();

        createdTruck.setCapacity(truck.getCapacity());

        List<LocalDateTime> arrivalTime = new ArrayList<>();
        arrivalTime.add(LocalDateTime.now());

        //DEBUG STATEMENTS:

//        for(LocalDateTime t : arrivalTime){
//            System.out.println(t);
//        }
        createdTruck.setArrivedAt(arrivalTime);

        //DEBUG STATEMENTS
      //  System.out.println(createdTruck.getArrivedAt());


       /*
       NULL POINTER EXCEPTION OCCURRED  IN THIS APPROACH:

       createdTruck.getArrivedAt().add(LocalDateTime.now());

        */

        createdTruck.setConsignmentVolume(0.0F);
        Truck savedTruck = truckRepository.save(createdTruck);

        //DEBUG STATEMENTS
       // System.out.println(savedTruck.getTruckId());
        return savedTruck;
    }

    @Override
    public Truck getTruckById(Integer TruckId) throws Exception {

        Optional<Truck> truckOptional = truckRepository.findById(TruckId);
        if(truckOptional.isEmpty()){
            throw new Exception("Truck Not Found with id : " +TruckId);
        }
        Truck truck = truckOptional.get();
        return truck;
    }

    @Override
    public List<Truck> getDetailOfAllTrucks() {
        return truckRepository.findAll();
    }

//    @Override
//    public Truck updateTruck(Integer truckId) {
//        return null;
//    }

    @Override
    public Object addConsignment(Integer truckId, Integer consId) throws Exception {
        Optional<Truck> truckOptional = truckRepository.findById(truckId);
        Optional<Consignment> consignmentOptional = consignmentRepository.findById(consId);

        if(consignmentOptional.isEmpty() || truckOptional.isEmpty()){
            throw new Exception("RESOURCE NOT FOUND EXCEPTION");
        }
        Truck truck = truckOptional.get();
        Consignment consignment = consignmentOptional.get();

        if(consignment.getTruck() != null){
           return ("CONSIGNMENT ALREADY ASSIGNED TO ANOTHER TRUCK");
        }

        truck.setConsignmentVolume(truck.getConsignmentVolume() + consignment.getVolume());
        truck.setConsignmentReceived(consId);

        if(truck.getConsignmentIds() == null){
            List<Integer> consIds = new ArrayList<>();
            consIds.add(consId);
            truck.setConsignmentIds(consIds);
        }
        else {
            truck.getConsignmentIds().add(consId);
        }

        if(truck.getReceivedAt() == null) {
            List<LocalDateTime> consignmentTimes = new ArrayList<>();
            consignmentTimes.add(LocalDateTime.now());
            truck.setReceivedAt(consignmentTimes);
        }
        else {
            truck.getReceivedAt().add(LocalDateTime.now());
        }

        truck.setDistanceTravelled(consignment.getDistanceBwSenderReceiver());
        truck.setReceiverAddress(consignment.getReceiverAddress());
        truck.setReceiverName(consignment.getReceiverName());
        truck.setSenderAddress(consignment.getSenderAddress());
        truck.setSenderName(consignment.getSenderName());
        truckRepository.save(truck);
        return truck;
    }

    @Override
    public Truck assignTruckToBranch(Integer truckId, Integer branchId) throws Exception {
        Optional<Truck> truckOptional = truckRepository.findById(truckId);
        Optional<BranchOffice> branchOffice = branchOfficeRepository.findById(branchId);

        if(truckOptional.isEmpty() || branchOffice.isEmpty()){
            throw new Exception("RESOURCE NOT FOUND EXCEPTION");
        }

        Truck truck = truckOptional.get();
        BranchOffice office = branchOffice.get();

        truck.setBranchOffice(office);
        truckRepository.save(truck);
        return truck;
    }
}
