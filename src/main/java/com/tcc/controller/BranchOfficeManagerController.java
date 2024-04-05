package com.tcc.controller;

import com.tcc.entity.BranchOffice;
import com.tcc.entity.Truck;
import com.tcc.service.BranchOfficeService;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
ALL FUNCTIONS RELATED TO THIS ARE
ADMIN ACTIONS i.e. PERFORMED BY MANAGER
 */
@RestController
@RequestMapping("/admin/api/office")
public class BranchOfficeManagerController {

    @Autowired
    private BranchOfficeService branchOfficeService;

    @Autowired
    private TruckService truckService;

    @PostMapping("/create")
    public ResponseEntity<BranchOffice> createBranchOffice(@RequestBody BranchOffice office){
      BranchOffice createdOffice =  branchOfficeService.createBranchOffice(office);
      return new ResponseEntity<>(createdOffice, HttpStatus.CREATED);
    }


    @PutMapping("/office/update/{id}")
    public ResponseEntity<BranchOffice> updateOffice(@PathVariable Integer id,
                                                     @RequestBody BranchOffice office) throws Exception {
        BranchOffice updateBranchOffice= branchOfficeService.updateBranchOffice(id, office);
        return new ResponseEntity<>(updateBranchOffice, HttpStatus.OK);
    }

    @PutMapping("/office/add/truck/{oId}/{tId}")
    public ResponseEntity<BranchOffice> addTruck(@PathVariable("oID") Integer oId,
                                                 @PathVariable("tId") Integer tId) throws Exception {

        BranchOffice office = branchOfficeService.addTrucks(oId, tId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @DeleteMapping("/office/delete/truck/{oId}/{tId}")
    public ResponseEntity<BranchOffice> deleteTruck(@PathVariable("oID") Integer oId,
                                                    @PathVariable("tId") Integer tId) throws Exception {

        BranchOffice office = branchOfficeService.deleteTrucks(oId, tId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @PutMapping("/office/add/consignment/{oId}/{cId}")
    public ResponseEntity<BranchOffice> addConsignment(@PathVariable("oID") Integer oId,
                                                 @PathVariable("cId") Integer cId) throws Exception {

        BranchOffice office = branchOfficeService.addConsignment(oId, cId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @DeleteMapping("/office/delete/consignment/{oId}/{cId}")
    public ResponseEntity<BranchOffice> deleteConsignment(@PathVariable("oID") Integer oId,
                                                    @PathVariable("cId") Integer cId) throws Exception {

        BranchOffice office = branchOfficeService.deleteConsignment(oId, cId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @PutMapping("/office/add/emp/{oId}/{eId}")
    public ResponseEntity<BranchOffice> addEmp(@PathVariable("oID") Integer oId,
                                                       @PathVariable("eId") Integer eId) throws Exception {

        BranchOffice office = branchOfficeService.addEmployees(oId, eId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @DeleteMapping("/office/delete/emp/{oId}/{eId}")
    public ResponseEntity<BranchOffice> deleteEmp(@PathVariable("oID") Integer oId,
                                                          @PathVariable("eId") Integer eId) throws Exception {

        BranchOffice office = branchOfficeService.deleteEmployees(oId, eId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }






}
