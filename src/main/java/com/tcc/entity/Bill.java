package com.tcc.entity;

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
    @OneToOne
    private Consignment consignmentDetails;

    /*

     */
    private Float totalAmount;

}
