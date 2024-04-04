package com.tcc.dto;

import com.tcc.entity.Roles;
import lombok.*;

import java.time.LocalDateTime;
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

    private Integer branchOfficeId;

}
