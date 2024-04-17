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

import java.util.ArrayList;
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
    public BranchOffice getBranchOfficeById(Integer id) throws Exception {
        Optional<BranchOffice> office = branchOfficeRepository.findById(id);
        if(office.isEmpty()){
            throw new Exception("Office Not Found With ID : " + id);
        }
        BranchOffice branchOffice = office.get();
        return branchOffice;
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
    public BranchOffice addTrucks(Integer branchId, Integer truckId) throws Exception {
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

        if(office.getTrucks() != null) {
            List<Truck> trucks = office.getTrucks();
            trucks.add(truck);
        }
        else{
            List<Truck> truckList = new ArrayList<>();
            truckList.add(truck);
            office.setTrucks(truckList);
        }

        truck.setBranchOffice(office);

        branchOfficeRepository.save(office);
        truckRepository.save(truck);
        return  office;
    }

    @Override
    public BranchOffice deleteTrucks(Integer branchId, Integer truckId) throws Exception {
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

        if(trucks.size() == 0){
            throw new Exception("NO TRUCKS FOUND IN THE BRANCH OFFICE");
        }

        if(trucks.contains(truck)){
            trucks.remove(truck);
            truck.setBranchOffice(null);
        }
        else {
            throw new Exception("Truck doesn't exist in the branch");
        }
        branchOfficeRepository.save(office);
        truckRepository.save(truck);
        return  office;
    }

    @Override
    public BranchOffice addConsignment(Integer branchId, Integer consId) throws Exception {
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

        if(office.getConsignments() != null) {
            List<Consignment> consgs = office.getConsignments();
            consgs.add(consg);
        }
        else{
            List<Consignment> consignmentList = new ArrayList<>();
            consignmentList.add(consg);
            office.setConsignments(consignmentList);
        }
        consg.setBranchOffice(office);

        branchOfficeRepository.save(office);
        consignmentRepository.save(consg);
        return  office;
    }

    @Override
    public BranchOffice deleteConsignment(Integer branchId, Integer consId) throws Exception {
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

        if(consgs.size() == 0){
            throw new Exception("NO CONSIGNMENTS ARRIVED AT THE BRANCH OFFICE");
        }

        if(consgs.contains(consg)){
            consgs.remove(consg);
            consg.setBranchOffice(null);
        }
        else{
           throw new Exception("Consignment not present in the BranchOffice");
        }

        branchOfficeRepository.save(office);
        consignmentRepository.save(consg);
        return  office;
    }

    @Override
    public BranchOffice addEmployees(Integer branchId, Integer empId) throws Exception {
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

        if(office.getEmployees() != null) {
            List<Employee> employees = office.getEmployees();
            employees.add(employee);
        }
        else{
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee);
            office.setEmployees(employeeList);
        }
        employee.setBranchOfficeId(office.getBranchId());
        employee.setBranchOffice(office);

        branchOfficeRepository.save(office);
        employeeRepository.save(employee);
        return  office;
    }

    @Override
    public BranchOffice deleteEmployees(Integer branchId, Integer empId) throws Exception {
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

        if(employees.size() == 0){
            throw new Exception("NO EMPLOYEE WERE ASSIGNED TO BRANCH OFFICE ");
        }
        if(employees.contains(employee)){
            employees.remove(employee);
            employee.setBranchOffice(null);
            employee.setBranchOfficeId(null);
        }
        else{
            throw new Exception("Employee doesn't exist in the branch Office");
        }
        branchOfficeRepository.save(office);
        employeeRepository.save(employee);
        return  office;
    }

    @Override
    public String deleteAllOffices() {
        branchOfficeRepository.deleteAll();
        return "ALL BRANCH OFFICES ARE DELETED";
    }

    @Override
    public String deleteOfficeById(Integer id) throws Exception {
        BranchOffice office = getBranchOfficeById(id);
        branchOfficeRepository.deleteById(id);
        return "BRANCH OFFICE WITH ID : " + id + " DELETED SUCCESSFULLY";
    }
}
