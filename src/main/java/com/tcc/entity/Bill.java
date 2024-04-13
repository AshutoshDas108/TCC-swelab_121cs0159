package com.tcc.entity;

import com.tcc.Types.BillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billId;

    private String senderAddress;

    private String receiverAddress;

    /*
    One Bill is related to a
    single consignment
     */
//    @OneToOne
//    private Consignment consignmentDetails;

    private String senderName;

    private String receiverName;

    private Float distanceCovered;

    private BillType type;

    /*
      Total Amount represents :
      (i) Total revenue generated once the consignment is delivered ;
      (ii) Total transport cost for the consignment
     */
    private Float totalAmount;


}
