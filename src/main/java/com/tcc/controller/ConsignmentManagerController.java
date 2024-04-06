package com.tcc.controller;

import com.tcc.entity.Bill;
import com.tcc.entity.Consignment;
import com.tcc.service.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/consignments")
public class ConsignmentManagerController {

    @Autowired
    private ConsignmentService consignmentService;

    @PostMapping("/create")
    public ResponseEntity<Consignment> createConsignment(@RequestBody Consignment consignment){
        Consignment createdConsignment = consignmentService.createConsignment(consignment);
        return new ResponseEntity<>(createdConsignment, HttpStatus.CREATED);
    }

    @PostMapping("/cost/{id}")
    public ResponseEntity<Bill> generateTransportCost(@PathVariable Integer id) throws Exception {
        Bill bill = consignmentService.generateTransportCost(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Consignment> updateConsignment(@PathVariable Integer id,
                                                         @RequestBody Consignment consignment) throws Exception {
        Consignment updatedCosnignment = consignmentService.updateConsignment(id, consignment);
        return new ResponseEntity<>(updatedCosnignment, HttpStatus.OK);
    }

    @PutMapping("/assign-truck/{id}")
    public ResponseEntity<Consignment> assignTruck(@PathVariable Integer id) throws Exception {
        Consignment consignment = consignmentService.assignTruck(id);
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }

    @PutMapping("/assign-office/{cId}/{oId}")
    public ResponseEntity<Consignment> assignOffice ( @PathVariable("cId") Integer cId,
                                                      @PathVariable("oId") Integer oId) throws Exception {
        Consignment consignment = consignmentService.assignOffice(cId, oId);
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }


}
