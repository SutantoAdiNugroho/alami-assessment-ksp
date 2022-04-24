package com.example.assessmentksp.controllers;

import com.example.assessmentksp.dto.deposit.DepositHistoryDto;
import com.example.assessmentksp.services.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/trx")
@AllArgsConstructor
public class TransactionController {

    private final DepositService depositService;

    @PostMapping("/deposit")
    public ResponseEntity<Object> createTrxDepo(@Valid @RequestBody DepositHistoryDto request) {
        return depositService.createTransactional(request);
    }

    @GetMapping("deposit/{memberId}")
    public ResponseEntity<Object> showDepositHistories(@PathVariable Long memberId) {
        return depositService.summaryHistories(memberId);
    }
}
