package com.tcc.service;

import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;

import java.util.List;

public interface ConsignmentService {
    public Consignment getAllConsignmentDetails();
    public List<Consignment> getConsignmentDetailById(Integer consId);
    public Bill generateTransportCost (Integer consId);
    public Consignment updateConsignment(Integer consId);
}
