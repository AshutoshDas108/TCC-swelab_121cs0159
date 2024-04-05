package com.tcc.controller;

import com.tcc.entity.BranchOffice;
import com.tcc.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BranchOfficeController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @GetMapping("/offices")
    public ResponseEntity<List<BranchOffice>> getAllOffices(){
        List<BranchOffice> branchOffices = branchOfficeService.getAllBranchOffice();
        return new ResponseEntity<>(branchOffices, HttpStatus.OK);
    }

    @GetMapping("/office/{id}")
    public ResponseEntity<BranchOffice> getOfficeDetails(@PathVariable Integer id) throws Exception {
        BranchOffice office = branchOfficeService.getBranchOfficeById(id);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }
}
