package com.example.assessmentksp.controllers;

import com.example.assessmentksp.dto.deposit.DepositHistoryDto;
import com.example.assessmentksp.services.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/deposit")
@AllArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @GetMapping("/{memberId}")
    public ResponseEntity<Object> summaryDeposit(@PathVariable Long memberId) {
        return depositService.summaryDeposit(memberId);
    }

    @PostMapping
    public ResponseEntity<Object> createTransactional(@Valid @RequestBody DepositHistoryDto request) {
        return depositService.createTransactional(request);
    }
}
