package com.tcc.service;

import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;

import java.util.List;

public interface ConsignmentService {
    public Consignment createConsignment(Consignment consignment);
    public List<Consignment> getAllConsignmentDetails();
    public Consignment getConsignmentDetailById(Integer consId) throws Exception;
    public Bill generateTransportCost (Integer consId);
    public Consignment updateConsignment(Integer consId);
    public Consignment assignTruck(Integer consId) throws Exception;
    public Consignment assignOffice(Integer consId, Integer branchId) throws Exception;
}
