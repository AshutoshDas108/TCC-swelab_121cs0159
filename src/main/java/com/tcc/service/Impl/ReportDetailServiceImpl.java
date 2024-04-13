package com.tcc.service.Impl;

import com.tcc.constants.ProjectConstants;
import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;
import com.tcc.entity.Truck;
import com.tcc.repository.BillRepository;
import com.tcc.service.ConsignmentService;
import com.tcc.service.ReportDetailsService;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportDetailServiceImpl implements ReportDetailsService {

    @Autowired
    private TruckService truckService;

    @Autowired
    private ConsignmentService consignmentService;

    @Autowired
    private BillRepository billRepository;

    @Override
    public LocalDateTime idleTimeOfTuck(Integer truckId) throws Exception {

        Truck truck = truckService.getTruckById(truckId);
        LocalDateTime arrivalTime = truck.getArrivedAt().get(truck.getArrivedAt().size()-1);
        LocalDateTime recievedTime = truck.getReceivedAt().get(truck.getReceivedAt().size()-1);
        return null;
    }

    @Override
    public LocalDateTime waitingTimeOfConsignment(Integer consId) throws Exception {
        Consignment consignment = consignmentService.getConsignmentDetailById(consId);
        LocalDateTime arrivalTime = consignment.getArrivedAt();
        LocalDateTime loadTime = consignment.getLoadedAt();
        return null;
    }

    @Override
    public Float calculateTotalReveneuGenerated(Integer truckId, Integer consId) throws Exception {
        Truck truck = truckService.getTruckById(truckId);
        Consignment consignment = consignmentService.getConsignmentDetailById(consId);
        Float vol = consignment.getVolume();
        Float distance = consignment.getDistanceBwSenderReceiver();
        Float TotalReveneu = vol * ProjectConstants.COST_OF_PER_CUBIC_METRE_OF_CONSIGNMENT +
                ProjectConstants.BOUNTY_ON_SUCCESSFUL_DELIVERY;
        return TotalReveneu;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Integer id) throws Exception {
        Optional<Bill> billOptional = billRepository.findById(id);
        if(billOptional.isEmpty()){
            throw new Exception("BILL NOT FOUND WITH ID :" + id);
        }
        Bill bill = billOptional.get();
        return bill;
    }

}
