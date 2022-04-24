package com.example.assessmentksp.services;

import com.example.assessmentksp.constants.TrxType;
import com.example.assessmentksp.dto.deposit.DepositHistoryDto;
import com.example.assessmentksp.dto.response.BaseResponse;
import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.DepositHistory;
import com.example.assessmentksp.repository.DepositHistoryRepository;
import com.example.assessmentksp.repository.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DepositService {
    private final DepositHistoryRepository depositHistoryRepository;
    private final DepositRepository depositRepository;

    public ResponseEntity<Object> summaryDeposit(Long memberId) {
        Deposit deposit = depositRepository.findByMemberId(memberId);
        return BaseResponse.successResponse("Successfully show deposit summary", HttpStatus.OK, deposit);
    }

    public ResponseEntity<Object> summaryHistories(Long memberId) {
        List<DepositHistory> deposit = depositHistoryRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);
        return BaseResponse.successResponse(String.format("Successfully show deposit histories summary with member_id: %s", memberId.toString()), HttpStatus.OK, deposit);
    }

    public ResponseEntity<Object> createTransactional(DepositHistoryDto request) {
        String message = "";
        Deposit deposit = depositRepository.findByMemberId(Long.parseLong(request.getMemberId()));
        if (deposit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Deposit with member_id %s not found", request.getMemberId()));
        }

        switch (request.getTrxType()) {
            case TrxType.TAKE:
                if (deposit.getBalance() - request.getTaken() <= 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Not enough funds to withdraw deposit, remaining balance Rp.%s", deposit.getBalance()));
                }
                Integer remainingTaken = deposit.getBalance() - request.getTaken();
                DepositHistory depositHistoryTaken = new DepositHistory(
                        request.getTaken(), deposit.getBalance(), remainingTaken, TrxType.TAKE
                );
                depositHistoryTaken.setMember(deposit.getMember());
                deposit.setBalance(remainingTaken);

                depositHistoryRepository.save(depositHistoryTaken);
                depositRepository.save(deposit);
                message = String.format("Successfully took funds of Rp.%s", request.getTaken());
                break;
            case TrxType.ADD:
                Integer remainingAdd = deposit.getBalance() + request.getTaken();
                DepositHistory depositHistoryAdd = new DepositHistory(
                        request.getTaken(), deposit.getBalance(), remainingAdd, TrxType.ADD
                );
                depositHistoryAdd.setMember(deposit.getMember());
                deposit.setBalance(remainingAdd);

                depositHistoryRepository.save(depositHistoryAdd);
                depositRepository.save(deposit);
                message = String.format("Successfully save funds of Rp.%s", request.getTaken());
                break;
        }
        return BaseResponse.successResponse(message, HttpStatus.OK, "deposit_funds");
    }
}
