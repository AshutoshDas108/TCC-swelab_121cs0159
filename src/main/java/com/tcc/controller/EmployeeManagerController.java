package com.tcc.controller;

import com.tcc.entity.BranchOffice;
import com.tcc.entity.Employee;
import com.tcc.service.BranchOfficeService;
import com.tcc.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class EmployeeManagerController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchOfficeService branchOfficeService;

    @PutMapping("/assign-office/{id}")
    public ResponseEntity<Employee>  assignOffice(@PathVariable Integer id) throws Exception {
        Employee emp = employeeService.assignBranchOffice(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmp(){
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/branch/{id}")
    public ResponseEntity<List<Employee>> getEmpAtBranchOffice(@PathVariable Integer id) throws Exception {
        List<Employee> employees = employeeService.getEmployeeByBranchOffice(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
