package com.tcc.controller;

import com.tcc.entity.Truck;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/truck")
public class TruckManagerController {

    @Autowired
    private TruckService truckService;

    @PostMapping("/create")
    public ResponseEntity<Truck> createTruck(@RequestBody Truck truck){
        Truck createdTruck = truckService.createTruck(truck);
        return new ResponseEntity<>(createdTruck, HttpStatus.CREATED);
    }

    @PutMapping("/add/consignment/{cId}/{tId}")
    public  ResponseEntity<Object> addConsignment(@PathVariable("cId") Integer cId,
                                                 @PathVariable("tId") Integer tId ) throws Exception {
        Object updatedTruck = truckService.addConsignment(tId, cId);
        return new ResponseEntity<>(updatedTruck, HttpStatus.OK);
    }

    @PutMapping("/assign/office/{oId}/{tId}")
    public ResponseEntity<Truck> assignOffice(@PathVariable("oId") Integer oId,
                                              @PathVariable("tId") Integer tId) throws Exception {
        Truck updatedTruck = truckService.assignTruckToBranch(tId, oId );
        return new ResponseEntity<>(updatedTruck, HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllTrucks(){
        String resp = truckService.deleteAll();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws Exception {
        String resp = truckService.deleteTruckById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
