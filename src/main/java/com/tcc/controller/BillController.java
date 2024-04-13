package com.tcc.controller;

import com.tcc.entity.Bill;
import com.tcc.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class BillController {

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills(){
          List<Bill> bills = reportDetailsService.getAllBills();
          return new ResponseEntity<>(bills, HttpStatus.OK);
    }

}
