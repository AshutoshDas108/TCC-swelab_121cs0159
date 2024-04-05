package com.tcc.service.Impl;

import com.tcc.entity.Bill;
import com.tcc.entity.BranchOffice;
import com.tcc.entity.Consignment;
import com.tcc.entity.Truck;
import com.tcc.repository.BranchOfficeRepository;
import com.tcc.repository.ConsignmentRepository;
import com.tcc.service.BranchOfficeService;
import com.tcc.service.ConsignmentService;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {

    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private TruckService truckService;

    @Autowired
    private BranchOfficeService branchOfficeService;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Override
    public Consignment createConsignment(Consignment consignment) {
        Consignment createdConsignment = new Consignment();
        createdConsignment.setDistanceBwSenderReceiver(consignment.getDistanceBwSenderReceiver());
        createdConsignment.setSenderAddress(consignment.getSenderAddress());
        createdConsignment.setSenderName(consignment.getSenderName());
        createdConsignment.setReceiverAddress(consignment.getReceiverAddress());
        createdConsignment.setReceiverName(consignment.getReceiverName());
        createdConsignment.setVolume(consignment.getVolume());
        createdConsignment.setIsDelivered(false);
        createdConsignment.setArrivedAt(LocalDateTime.now());

        Consignment savedConsignment = consignmentRepository.save(createdConsignment);
        return savedConsignment;
    }

    @Override
    public List<Consignment> getAllConsignmentDetails() {
        return consignmentRepository.findAll();
    }

    @Override
    public Consignment getConsignmentDetailById(Integer consId) throws Exception {
        Optional<Consignment> consignmentOptional = consignmentRepository.findById(consId);
        if(consignmentOptional.isEmpty()){
            throw new Exception("CONSIGNMENT NOT FOUND WITH ID : " + consId);
        }
        Consignment consignment = consignmentOptional.get();
        return consignment;
    }

    @Override
    public Bill generateTransportCost(Integer consId) {
        return null;
    }

    @Override
    public Consignment updateConsignment(Integer consId) {
        return null;
    }

    @Override
    public Consignment assignTruck(Integer consId) throws Exception {
        Consignment consignment = getConsignmentDetailById(consId);
        List<Truck> trucks = truckService.getDetailOfAllTrucks();
        for (Truck truck : trucks) {
            Float cap = truck.getCapacity();
            Float load = truck.getConsignmentVolume();
            Float availableVol = cap - load;
            Float consignmentVol = consignment.getVolume();
            if (availableVol >= consignmentVol) {
                consignment.setTruck(truck);
                consignmentRepository.save(consignment);
                break;
            }
        }
        if(consignment.getTruck() == null){
            throw new Exception("NO TRUCK AVAILABLE CURRENTLY TO LOAD THE CONSIGNMENT PLEASE WAIT");
        }
        else{
            return consignment;
        }
    }

    @Override
    public Consignment assignOffice(Integer consId, Integer branchId) throws Exception {
        Consignment consignment = getConsignmentDetailById(consId);
        BranchOffice office = branchOfficeService.getBranchOfficeById(branchId);
        consignment.setBranchOffice(office);
        office.getConsignments().add(consignment);
        consignmentRepository.save(consignment);
        branchOfficeRepository.save(office);
        return consignment;
    }
}
