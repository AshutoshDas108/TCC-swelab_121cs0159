package com.tcc.service.Impl;

import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;
import com.tcc.service.ConsignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {
    @Override
    public Consignment getAllConsignmentDetails() {
        return null;
    }

    @Override
    public List<Consignment> getConsignmentDetailById(Integer consId) {
        return null;
    }

    @Override
    public Bill generateTransportCost(Integer consId) {
        return null;
    }

    @Override
    public Consignment updateConsignment(Integer consId) {
        return null;
    }
}
