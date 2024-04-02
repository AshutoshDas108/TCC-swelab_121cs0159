package com.tcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer consignmentId;

    private Float Volume;

    private Integer totalConsignments;

    private String senderAddress;

    private String receiverAddress;

    /*
    Details of the branchOffice to which
    the consignment had arrived
     */
    @ManyToOne
    private BranchOffice branchOffice;

    /*
    Which truck was assigned the
    consignment
     */
    @ManyToOne
    private Truck truck;

    /*
    The time at which the consignment
    arrived to the branch office
     */
    private LocalDateTime arrivedAt;

    /*
    The time at which the consignment
    is loaded to a truck for transportation
     */
    private LocalDateTime loadedAt;
}
