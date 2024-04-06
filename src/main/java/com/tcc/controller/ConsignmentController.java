package com.tcc.controller;

import com.tcc.entity.Consignment;
import com.tcc.service.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consignments")
public class ConsignmentController {

    @Autowired
    private ConsignmentService consignmentService;

    @GetMapping()
    public ResponseEntity<List<Consignment>> getAllConsignments(){
        List<Consignment> consignments = consignmentService.getAllConsignmentDetails();
        return new ResponseEntity<>(consignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consignment> getConsignmentById(@PathVariable Integer id) throws Exception {
        Consignment consignment = consignmentService.getConsignmentDetailById(id);
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }
}
