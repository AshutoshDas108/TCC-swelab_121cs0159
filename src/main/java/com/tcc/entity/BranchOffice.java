package com.tcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer branchId;

    private boolean isHeadOffice;

    private String loc;

    /*
    Details of all the trucks that
    are currently present in the branch office
     */
    @OneToMany(mappedBy = "branchOffice")
    private ArrayList<Truck> trucks;

    /*
    Details of all the consignments that arrive at
    that particular branch office
     */
    @OneToMany(mappedBy = "branchOffice")
    private ArrayList<Consignment> consignments;
}
