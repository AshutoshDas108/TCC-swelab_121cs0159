package com.tcc.dto;

import com.tcc.Types.Roles;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private String empName;

    private String email;

    private Roles role;

    private Date dateOfJoining;

//    private Integer branchOfficeId;

}
