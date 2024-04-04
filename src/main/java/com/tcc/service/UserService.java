package com.tcc.service;

import com.tcc.entity.Employee;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    public Employee findEmployeeByJwtToken (String jwt) throws UsernameNotFoundException;

    public Employee findEmployeeByEmail (String email) throws UsernameNotFoundException;

    public Employee updateEmployeeById(Integer empId) throws UsernameNotFoundException;

}
