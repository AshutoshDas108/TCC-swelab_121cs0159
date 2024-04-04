package com.tcc.dto.mapper;

import com.tcc.dto.EmployeeDto;
import com.tcc.entity.Employee;


public class EmployeeDtoMapper {

    public static EmployeeDto mapToEmployeeDto(Employee emp, EmployeeDto empDto){

        empDto.setEmpName(emp.getEmpName());
        empDto.setEmail(emp.getEmail());

        if(empDto.getBranchOfficeId()!=null) {
            empDto.setBranchOfficeId(emp.getBranchOfficeId());
        }
        empDto.setDateOfJoining(emp.getDateOfJoining());
        empDto.setRole(emp.getUserRole());

        return  empDto;
    }

    public static Employee mapToEmployee (Employee emp, EmployeeDto empDto){

        emp.setEmpName(empDto.getEmpName());
        emp.setEmail(empDto.getEmail());

        if(emp.getBranchOfficeId()!=null) {
            emp.setBranchOfficeId(empDto.getBranchOfficeId());
        }
        emp.setUserRole(empDto.getRole());
        emp.setDateOfJoining(empDto.getDateOfJoining());

        return emp;
    }
}
