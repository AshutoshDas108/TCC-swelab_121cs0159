package com.tcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.results.spi.LoadContexts;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    private String senderName;

    private String senderAddress;

    private String receiverName;

    private String receiverAddress;

    /*
    In which branch Office the truck
    is currently available
     */
    @ManyToOne
    private BranchOffice branchOffice;

    /*
    Time at which the truck arrived at the
    new branch office everytime
     */
    private ArrayList<LocalDateTime> arrivedAt;

    /*
    Time at which the truck receives its
    first consignment after reaching the
    particular branch Office
     */
    private ArrayList<LocalDateTime> receivedAt;

    /*
    the history of arrival and receiving
    consignments are maintained to calculate
    average idle time for the truck
     */


}