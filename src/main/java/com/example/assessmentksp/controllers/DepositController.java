package com.example.assessmentksp.controllers;

import com.example.assessmentksp.services.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/deposit")
@AllArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @GetMapping("")
    public ResponseEntity<Object> getAllDeposit(@RequestParam(required = false) String memberId) {
        return depositService.getAllDeposit(memberId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepositById(@PathVariable Long id) {
        return depositService.getDepositById(id);
    }
}
