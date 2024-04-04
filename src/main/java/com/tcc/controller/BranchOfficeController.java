package com.tcc.controller;

import com.tcc.entity.BranchOffice;
import com.tcc.service.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
ALL FUNCTIONS RELATED TO THIS ARE
ADMIN ACTIONS i.e. PERFORMED BY MANAGER
 */
@RestController
@RequestMapping("/admin/api/office")
public class BranchOfficeController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @PostMapping("/create")
    public ResponseEntity<BranchOffice> createBranchOffice(@RequestBody BranchOffice office){
      BranchOffice createdOffice =  branchOfficeService.createBranchOffice(office);
      return new ResponseEntity<>(createdOffice, HttpStatus.CREATED);
    }
}
