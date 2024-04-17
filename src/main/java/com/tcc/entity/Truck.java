package com.tcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer truckId;

    /*
    id of the received consignment
    Null if truck is free i.e. it
    has not received any consignment
     */
    private Integer consignmentReceived;

    /*
    Volume of the consignment the truck had
    received , 0 if truck is free
     */
    private Float consignmentVolume;

    /*
    Maximum volume the truck can accommodate
     */
    private Float Capacity;

    private String senderName;

    private String senderAddress;

    private String receiverName;

    private String receiverAddress;

    /*
    Distance Between Sender and Receiver Address
     */
    private Float distanceTravelled;

    /*
    In which branch Office the truck
    is currently available
     */
    @ManyToOne
    @JoinColumn(name = "branchId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BranchOffice branchOffice;

    /*
    Time at which the truck arrived at the
    new branch office everytime
     */
    private List<LocalDateTime> arrivedAt;

    /*
    Time at which the truck receives its
    first consignment after reaching the
    particular branch Office
     */
    private List<LocalDateTime> receivedAt;

    /*
    the history of consignments are maintained
     */
    private List<Integer> consignmentIds;



}
