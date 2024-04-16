package com.tcc.service.Impl;

import com.tcc.config.jwt.JwtProvider;
import com.tcc.dto.EmployeeDto;
import com.tcc.entity.BranchOffice;
import com.tcc.entity.Employee;
import com.tcc.repository.BranchOfficeRepository;
import com.tcc.repository.EmployeeRepository;
import com.tcc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Override
    public Employee findEmployeeByJwtToken(String jwt) throws UsernameNotFoundException {
        String userEmail = jwtProvider.getEmailFromJwt(jwt);

        /*
        This is for debugging purpose : to check if we are receiving
        the correct email or if we are getting null values

        System.out.println(userEmail);
         */

        Optional<Employee> emp = employeeRepository.findByEmail(userEmail);

        if(emp.isEmpty()){
            throw new UsernameNotFoundException("Invalid Token....");
        }

        return emp.get();
    }


    /*
    THIS FUNCTION IS NOT NEEDED --> already email is extracted using jwt and that email is used to
    find the user

    just to avoid repeated calling to the database we designed this function
    */

    @Override
    public Employee getEmployeeByEmail(String email) throws UsernameNotFoundException {
        Optional<Employee> emp = employeeRepository.findByEmail(email);
        if(emp.isEmpty()){
            throw new UsernameNotFoundException("Email does not exist....");
        }
        return emp.get();
    }


    @Override
    public Employee updateEmployee(Integer empId, Employee newEmp) throws UsernameNotFoundException {
        Optional<Employee> empDb = employeeRepository.findById(empId);
        if(empDb.isEmpty()){
            //System.out.println("NO USER");
            throw  new UsernameNotFoundException("User not found with empId" + empId);
        }
        Employee emp = empDb.get();
        if( newEmp.getEmpName() != null){
            emp.setEmpName(newEmp.getEmpName());
        }
        if(newEmp.getEmail() != null){
           // System.out.println(newEmp.getEmail());
            emp.setEmail(newEmp.getEmail());
        }
        if(newEmp.getBranchOfficeId() != null){
            emp.setBranchOfficeId(newEmp.getBranchOfficeId());
        }
        if(newEmp.getUserRole() != null){
            System.out.println(newEmp.getUserRole());
            emp.setUserRole(newEmp.getUserRole());
        }
        return  employeeRepository.save(emp);
    }

    @Override
    public Employee assignBranchOffice(Integer userId) throws Exception {
        Optional<Employee> empOpt = employeeRepository.findById(userId);
        if(empOpt.isEmpty()){
            throw new UsernameNotFoundException("User Not found with id : " + userId);
        }
        Employee emp = empOpt.get();

        List<BranchOffice> offices = branchOfficeRepository.findAll();
        if(offices.isEmpty()){
            throw new Exception("NO BRANCH OFFICES ARE ESTABLISHED YET WAIT TILL OFFICES OPEN");
        }

        int l = offices.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt((l-1)-0 + 1 );
        BranchOffice office = offices.get(randomIndex);

        emp.setBranchOffice(office);
        emp.setBranchOfficeId(office.getBranchId());

        office.getEmployees().add(emp);

        employeeRepository.save(emp);
        branchOfficeRepository.save(office);

        return emp;

    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            throw new UsernameNotFoundException("User Not found with id : " + id);
        }
        Employee employee = employeeOptional.get();
        return employee;
    }

    @Override
    public List<Employee> getEmployeeByBranchOffice(Integer branchId) throws Exception {
        Optional<BranchOffice> optionalBranchOffice = branchOfficeRepository.findById(branchId);
        if(optionalBranchOffice.isEmpty()){
            throw new Exception("Branch Office Not Found With Id : " + branchId);
        }
        List<Employee> listOfEmp = optionalBranchOffice.get().getEmployees();

        return listOfEmp;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public String deleteEmployees() {
        employeeRepository.deleteAll();
        return "All employees deleted successfully";
    }

}
