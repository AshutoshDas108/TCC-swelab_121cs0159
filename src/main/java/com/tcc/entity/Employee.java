package com.tcc.entity;

import com.tcc.Types.Roles;
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

    private Roles userRole = Roles.ROLE_ITSTAFF;

    private Integer branchOfficeId;

    @ManyToOne
    private BranchOffice branchOffice;

    private Date dateOfJoining;

}
