package com.tcc.dto;

import com.tcc.entity.Consignment;
import com.tcc.entity.Employee;
import com.tcc.entity.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchOfficeDto {

    Integer branchId;

    String loc;

    Boolean isHeadOffice;

    List<Truck> trucks;

    List<Consignment> consignments;

    List<Employee> employees;

}
