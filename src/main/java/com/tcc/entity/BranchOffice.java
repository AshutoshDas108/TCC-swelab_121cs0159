package com.tcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer branchId;

    private Boolean isHeadOffice;

    private String loc;

    /*
    Details of all the trucks that
    are currently present in the branch office
     */
    @JsonIgnore
    @OneToMany(mappedBy = "branchOffice", cascade = CascadeType.ALL)
    private List<Truck> trucks;

    /*
    Details of all the consignments that arrive at
    that particular branch office
     */

    @JsonIgnore
    @OneToMany(mappedBy = "branchOffice", cascade = CascadeType.ALL)
    private List<Consignment> consignments;

    @JsonIgnore
    @OneToMany(mappedBy = "branchOffice", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
