package com.tcc.config;

import com.tcc.entity.Employee;
import com.tcc.repository.EmployeeRepository;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class TccUserDetailService implements UserDetailsService {

    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee>  employee = employeeRepository.findByEmail(username);
        return null;
    }
}
