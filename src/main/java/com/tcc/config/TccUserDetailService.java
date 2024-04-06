package com.tcc.config;

import com.tcc.entity.Employee;
import com.tcc.Types.Roles;
import com.tcc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TccUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee>  employee = employeeRepository.findByEmail(username);

        if(employee.isEmpty()){
            throw  new UsernameNotFoundException("user not found with username " + username);
        }

        Roles employeeRole = employee.get().getUserRole();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(employeeRole.toString()));
        return new User(employee.get().getEmail(), employee.get().getPassword(), authorities);
    }
}
