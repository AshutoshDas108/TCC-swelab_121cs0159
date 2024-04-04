package com.tcc.service;

import com.tcc.entity.BranchOffice;

public interface BranchOfficeService {
    public BranchOffice updateBranchOffice (Integer branchId);
    public BranchOffice addTrucks(Integer branchId, Integer truckId);
    public  BranchOffice addConsignment (Integer branchId, Integer consId);
}
