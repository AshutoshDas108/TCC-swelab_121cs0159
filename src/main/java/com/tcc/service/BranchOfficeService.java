package com.tcc.service;

import com.tcc.entity.BranchOffice;

import java.util.List;

public interface BranchOfficeService {
    public BranchOffice createBranchOffice(BranchOffice office);
    public List<BranchOffice> getAllBranchOffice();
    public  BranchOffice getBranchOfficeById(Integer id) throws Exception;
    public BranchOffice updateBranchOffice (Integer branchId, BranchOffice office) throws Exception;
    public BranchOffice addTrucks(Integer branchId, Integer truckId) throws Exception;
    public BranchOffice deleteTrucks(Integer branchId, Integer truckId) throws Exception;
    public  BranchOffice addConsignment (Integer branchId, Integer consId) throws Exception;
    public  BranchOffice deleteConsignment (Integer branchId, Integer consId) throws Exception;
    public BranchOffice addEmployees(Integer branchId, Integer empId) throws Exception;
    public BranchOffice deleteEmployees(Integer branchId, Integer empId) throws Exception;
    public String deleteAllOffices();
    public String deleteOfficeById(Integer id) throws Exception;
}
