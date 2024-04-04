package com.tcc.service.Impl;

import com.tcc.entity.BranchOffice;
import com.tcc.entity.Consignment;
import com.tcc.entity.Employee;
import com.tcc.entity.Truck;
import com.tcc.repository.BranchOfficeRepository;
import com.tcc.repository.ConsignmentRepository;
import com.tcc.repository.EmployeeRepository;
import com.tcc.repository.TruckRepository;
import com.tcc.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public BranchOffice createBranchOffice(BranchOffice office) {
        BranchOffice branchOffice = new BranchOffice();
        if(office.getLoc()!=null){
            branchOffice.setLoc(office.getLoc());
        }
        if(office.getIsHeadOffice()!=null){
            branchOffice.setIsHeadOffice(office.getIsHeadOffice());
        }
         BranchOffice savedBranchOffice = branchOfficeRepository.save(branchOffice);
        return savedBranchOffice;
    }

    @Override
    public List<BranchOffice> getAllBranchOffice() {
        return branchOfficeRepository.findAll();
    }

    @Override
    public BranchOffice updateBranchOffice(Integer branchId, BranchOffice office) throws Exception {
        Optional<BranchOffice> off = branchOfficeRepository.findById(branchId);
        if(off.isEmpty()){
            throw new Exception("Branch Not Found with id :" + branchId);
        }

        BranchOffice oldOffice= off.get();
        if(office.getIsHeadOffice()!= null){
            oldOffice.setIsHeadOffice(office.getIsHeadOffice());
        }
        if(office.getLoc()!=null) {
            oldOffice.setLoc(office.getLoc());
        }

        branchOfficeRepository.save(oldOffice);
        return oldOffice;
    }

    @Override
    public BranchOffice editTrucks(Integer branchId, Integer truckId) throws Exception {
        Optional<Truck> truckDb = truckRepository.findById(truckId);
        Optional<BranchOffice> bof = branchOfficeRepository.findById(branchId);

        if(bof.isEmpty()){
            throw new Exception("Branch does not exist with Id :" + branchId);
        }

        if(truckDb.isEmpty()){
            throw new Exception("Truck Not Found with Id :" + truckId);
        }

        BranchOffice office = bof.get();
        Truck truck = truckDb.get();

        List<Truck> trucks = office.getTrucks();
        if(trucks.contains(truck)){
            trucks.remove(truck);
        }
        else{
            trucks.add(truck);
        }

        branchOfficeRepository.save(office);
        return  office;
    }

    @Override
    public BranchOffice editConsignment(Integer branchId, Integer consId) throws Exception {
        Optional<Consignment> consDb = consignmentRepository.findById(consId);
        Optional<BranchOffice> bof = branchOfficeRepository.findById(branchId);

        if(bof.isEmpty()){
            throw new Exception("Branch does not exist with Id :" + branchId);
        }

        if(consDb.isEmpty()){
            throw new Exception("Consignment Not Found with Id :" + consId);
        }

        BranchOffice office = bof.get();
        Consignment consg = consDb.get();

        List<Consignment> consgs = office.getConsignments();
        if(consgs.contains(consg)){
            consgs.remove(consg);
        }
        else{
            consgs.add(consg);
        }

        branchOfficeRepository.save(office);
        return  office;
    }

    @Override
    public BranchOffice editEmployees(Integer branchId, Integer empId) throws Exception {
        Optional<Employee> empDb = employeeRepository.findById(empId);
        Optional<BranchOffice> bof = branchOfficeRepository.findById(branchId);

        if(bof.isEmpty()){
            throw new Exception("Branch does not exist with Id :" + branchId);
        }

        if(empDb.isEmpty()){
            throw new Exception("Employee Not Found with Id :" + empId);
        }

        BranchOffice office = bof.get();
        Employee employee = empDb.get();

        List<Employee> employees = office.getEmployees();
        if(employees.contains(employee)){
            employees.remove(employee);
        }
        else{
            employees.add(employee);
        }

        branchOfficeRepository.save(office);
        return  office;
    }


}
