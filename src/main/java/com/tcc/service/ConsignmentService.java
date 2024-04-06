package com.tcc.service;

import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;

import java.util.List;

public interface ConsignmentService {
    public Consignment createConsignment(Consignment consignment);
    public List<Consignment> getAllConsignmentDetails();
    public Consignment getConsignmentDetailById(Integer consId) throws Exception;
    public Bill generateTransportCost (Integer consId) throws Exception;
    public Consignment updateConsignment(Integer consId, Consignment consignment) throws Exception;
    public Object assignTruck(Integer consId) throws Exception;
    public Consignment assignOffice(Integer consId, Integer branchId) throws Exception;
}
