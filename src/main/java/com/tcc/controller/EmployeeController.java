package com.tcc.controller;

import com.tcc.dto.EmployeeDto;
import com.tcc.dto.mapper.EmployeeDtoMapper;
import com.tcc.entity.Employee;
import com.tcc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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

}
