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

    /*
    Status of the consignment
     */
    private Boolean isDelivered;

    private Boolean isBillGenerated;

    private String senderAddress;

    private String receiverAddress;

    private String senderName;

    private String receiverName;

    private Float distanceBwSenderReceiver;
    /*
    Details of the branchOffice to which
    the consignment had arrived
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BranchOffice branchOffice;

    /*
    Which truck was assigned the
    consignment
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truckId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
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
