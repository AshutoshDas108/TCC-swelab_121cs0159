package com.tcc.controller;

import com.tcc.entity.Truck;
import com.tcc.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping()
    public ResponseEntity<List<Truck>> getAllTrucks(){
        List<Truck> trucks = truckService.getDetailOfAllTrucks();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> getTruckById(@PathVariable Integer id) throws Exception {
        Truck truck = truckService.getTruckById(id);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getCurrentStatus(@PathVariable Integer id) throws Exception {
        String resp = truckService.getCurrentStatusOfTruck(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
