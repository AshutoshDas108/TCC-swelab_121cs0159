package com.tcc.service;

import com.tcc.entity.BranchOffice;

import java.util.List;

public interface BranchOfficeService {
    public BranchOffice createBranchOffice(BranchOffice office);
    public List<BranchOffice> getAllBranchOffice();
    public BranchOffice updateBranchOffice (Integer branchId, BranchOffice office) throws Exception;
    public BranchOffice editTrucks(Integer branchId, Integer truckId) throws Exception;
    public  BranchOffice editConsignment (Integer branchId, Integer consId) throws Exception;
    public BranchOffice editEmployees(Integer branchId, Integer empId) throws Exception;
}
