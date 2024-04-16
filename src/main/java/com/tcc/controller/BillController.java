package com.tcc.controller;

import com.tcc.entity.Bill;
import com.tcc.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/bills")
public class BillController {

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("")
    public ResponseEntity<List<Bill>> getAllBills(){
          List<Bill> bills = reportDetailsService.getAllBills();
          return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll(){
        String resp = reportDetailsService.deleteAllBills();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws Exception {
        String resp = reportDetailsService.deleteBillsById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
