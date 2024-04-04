package com.tcc.service.Impl;

import com.tcc.config.jwt.JwtProvider;
import com.tcc.dto.EmployeeDto;
import com.tcc.entity.Employee;
import com.tcc.repository.EmployeeRepository;
import com.tcc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public Employee findEmployeeByJwtToken(String jwt) throws UsernameNotFoundException {
        String userEmail = jwtProvider.getEmailFromJwt(jwt);

        System.out.println(userEmail);

        Optional<Employee> emp = employeeRepository.findByEmail(userEmail);

        if(emp.isEmpty()){
            throw new UsernameNotFoundException("Invalid Token....");
        }

        return emp.get();
    }

    /*
    @Override
    public Employee findEmployeeByEmail(String email) throws UsernameNotFoundException {
        Optional<Employee> emp = employeeRepository.findByEmail(email);
        if(emp.isEmpty()){
            throw new UsernameNotFoundException("Email does not exist....");
        }
        return emp.get();
    }
     */

    @Override
    public Employee updateEmployee(Integer empId, Employee newEmp) throws UsernameNotFoundException {
        Optional<Employee> empDb = employeeRepository.findById(empId);
        if(empDb.isEmpty()){
            throw  new UsernameNotFoundException("User not found with empId" + empId);
        }
        Employee emp = empDb.get();
        if( newEmp.getEmpName() != null){
            emp.setEmpName(newEmp.getEmpName());
        }
        if(newEmp.getEmail() != null){
            emp.setEmail(newEmp.getEmail());
        }
        if(newEmp.getBranchOfficeId() != null){
            emp.setBranchOfficeId(newEmp.getBranchOfficeId());
        }
        if(newEmp.getUserRole() != null){
            emp.setUserRole(newEmp.getUserRole());
        }
        return  employeeRepository.save(emp);
    }

}
