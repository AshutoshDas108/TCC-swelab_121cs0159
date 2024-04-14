package com.tcc.controller;

import com.tcc.dto.EmployeeDto;
import com.tcc.dto.mapper.EmployeeDtoMapper;
import com.tcc.entity.Employee;
import com.tcc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/profiles")
    public ResponseEntity<EmployeeDto> getEmployeeByJwt(@RequestHeader("Authorization") String jwt){
         Employee employee = employeeService.findEmployeeByJwtToken(jwt);
         return new ResponseEntity<>(EmployeeDtoMapper.mapToEmployeeDto(employee, new EmployeeDto()), HttpStatus.OK);
    }

    @PutMapping("/profiles/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestHeader("Authorization") String jwt, @RequestBody Employee newEmp ){
        Employee employee = employeeService.findEmployeeByJwtToken(jwt);
        Integer empId = employee.getEmpId();

        Employee updatedEmp = employeeService.updateEmployee(empId, newEmp);

        EmployeeDto updateEmpDto = EmployeeDtoMapper.mapToEmployeeDto(updatedEmp, new EmployeeDto());

        return new ResponseEntity<>(updateEmpDto, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmp(){
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable Integer id){
        Employee employee = employeeService.getEmployeeById(id);
        //EmployeeDto employeeDto = EmployeeDtoMapper.mapToEmployeeDto(employee, new EmployeeDto());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
