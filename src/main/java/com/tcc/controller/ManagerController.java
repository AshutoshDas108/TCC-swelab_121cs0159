package com.tcc.controller;

import com.tcc.entity.Employee;
import com.tcc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class ManagerController {

    @Autowired
    private EmployeeService employeeService;

    @PutMapping("/assign-office/{id}")
    public ResponseEntity<Employee>  assignOffice(@PathVariable Integer id) throws Exception {
        Employee emp = employeeService.assignBranchOffice(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
