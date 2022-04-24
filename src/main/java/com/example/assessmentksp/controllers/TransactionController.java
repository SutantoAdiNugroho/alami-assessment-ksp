package com.example.assessmentksp.controllers;

import com.example.assessmentksp.dto.deposit.DepositHistoryDto;
import com.example.assessmentksp.dto.loan.GetLoansDto;
import com.example.assessmentksp.dto.loan.LoanPaymentDto;
import com.example.assessmentksp.dto.loan.RegistrationLoanDto;
import com.example.assessmentksp.services.DepositService;
import com.example.assessmentksp.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/trx")
@AllArgsConstructor
public class TransactionController {
    private final DepositService depositService;
    private final LoanService loanService;


    @GetMapping("/deposit")
    public ResponseEntity<Object> showDepositHistories() {
        return depositService.getAllDepositHistories();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> createTrxDepo(@Valid @RequestBody DepositHistoryDto request) {
        return depositService.createTransactional(request);
    }

    @GetMapping("/loan")
    public ResponseEntity<Object> showLoanHistories() {
        return loanService.getAllLoanHistories();
    }

    @PostMapping("/loan")
    public ResponseEntity<Object> createTrxLoan(@Valid @RequestBody RegistrationLoanDto request) {
        return loanService.createLoan(request);
    }

    @PostMapping("/loan/payment")
    public ResponseEntity<Object> createLoanPayment(@Valid @RequestBody LoanPaymentDto request) {
        return loanService.createLoanPayment(request);
    }
}
