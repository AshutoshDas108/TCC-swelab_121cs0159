package com.tcc.service;

import com.tcc.entity.Employee;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface EmployeeService {

    public Employee findEmployeeByJwtToken (String jwt) throws UsernameNotFoundException;

    public Employee updateEmployee(Integer empId, Employee newEmp) throws UsernameNotFoundException;

    public Employee assignBranchOffice(Integer userId) throws Exception;

    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeByEmail(String email);

    public List<Employee>  getEmployeeByBranchOffice(Integer branchId) throws Exception;

    public List<Employee> getAllEmployee();

    public String deleteEmployees();



}
