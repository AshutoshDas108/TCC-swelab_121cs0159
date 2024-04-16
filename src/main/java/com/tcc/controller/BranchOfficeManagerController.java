package com.tcc.controller;

import com.tcc.dto.BranchOfficeDto;
import com.tcc.dto.mapper.BranchOfficeDtoMapper;
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


    @PutMapping("/update/{id}")
    public ResponseEntity<BranchOffice> updateOffice(@PathVariable Integer id,
                                                     @RequestBody BranchOffice office) throws Exception {
        BranchOffice updateBranchOffice= branchOfficeService.updateBranchOffice(id, office);
        return new ResponseEntity<>(updateBranchOffice, HttpStatus.OK);
    }

    @PutMapping("/add/truck/{oId}/{tId}")
    public ResponseEntity<BranchOfficeDto> addTruck(@PathVariable("oId") Integer oId,
                                                    @PathVariable("tId") Integer tId) throws Exception {

        BranchOffice office = branchOfficeService.addTrucks(oId, tId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @PutMapping("/delete/truck/{oId}/{tId}")
    public ResponseEntity<BranchOfficeDto> deleteTruck(@PathVariable("oId") Integer oId,
                                                    @PathVariable("tId") Integer tId) throws Exception {

        BranchOffice office = branchOfficeService.deleteTrucks(oId, tId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @PutMapping("/add/consignment/{oId}/{cId}")
    public ResponseEntity<BranchOfficeDto> addConsignment(@PathVariable("oId") Integer oId,
                                                 @PathVariable("cId") Integer cId) throws Exception {

        BranchOffice office = branchOfficeService.addConsignment(oId, cId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @PutMapping("/delete/consignment/{oId}/{cId}")
    public ResponseEntity<BranchOfficeDto> deleteConsignment(@PathVariable("oId") Integer oId,
                                                    @PathVariable("cId") Integer cId) throws Exception {

        BranchOffice office = branchOfficeService.deleteConsignment(oId, cId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @PutMapping("/add/emp/{oId}/{eId}")
    public ResponseEntity<BranchOfficeDto> addEmp(@PathVariable("oId") Integer oId,
                                                       @PathVariable("eId") Integer eId) throws Exception {

        BranchOffice office = branchOfficeService.addEmployees(oId, eId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @PutMapping("/delete/emp/{oId}/{eId}")
    public ResponseEntity<BranchOfficeDto> deleteEmp(@PathVariable("oId") Integer oId,
                                                          @PathVariable("eId") Integer eId) throws Exception {

        BranchOffice office = branchOfficeService.deleteEmployees(oId, eId);
        BranchOfficeDto officeDto = BranchOfficeDtoMapper.boffToDto(office, new BranchOfficeDto());
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/all")
    public ResponseEntity<String> deleteAll(){
        String resp = branchOfficeService.deleteAllOffices();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws Exception {
        String resp = branchOfficeService.deleteOfficeById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }






}
