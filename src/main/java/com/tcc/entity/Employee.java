package com.tcc.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empId;

    private String empName;

    private String email;

    private String password;

    private Roles userRole = Roles.ROLE_IT_STAFF;

    private Integer branchOfficeId;

    private Date dateOfJoining;

}
