package com.tcc.service;

import com.tcc.entity.Bill;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportDetailsService {
    public LocalDateTime idleTimeOfTuck(Integer truckId) throws Exception;
    public LocalDateTime waitingTimeOfConsignment (Integer consId) throws Exception;
    public Float calculateTotalReveneuGenerated(Integer truckId, Integer consId) throws Exception;
    public List<Bill> getAllBills();
    public Bill getBillById(Integer id) throws Exception;
    public String deleteAllBills();
    public String deleteBillsById(Integer id) throws Exception;
}
