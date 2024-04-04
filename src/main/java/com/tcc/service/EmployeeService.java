package com.tcc.service;

import com.tcc.entity.Employee;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface EmployeeService {

    public Employee findEmployeeByJwtToken (String jwt) throws UsernameNotFoundException;

    public Employee updateEmployee(Integer empId, Employee newEmp) throws UsernameNotFoundException;



}
