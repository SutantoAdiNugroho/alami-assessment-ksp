package com.example.assessmentksp.controllers;

import com.example.assessmentksp.services.DepositService;
import com.example.assessmentksp.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/loan")
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("")
    public ResponseEntity<Object> showAllLoans(@RequestParam(required = false) String memberId) {
        return loanService.getAllLoan(memberId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }
}
