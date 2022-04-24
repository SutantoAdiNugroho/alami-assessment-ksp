package com.example.assessmentksp.services;

import com.example.assessmentksp.constants.TrxType;
import com.example.assessmentksp.dto.deposit.DepositHistoryDto;
import com.example.assessmentksp.dto.response.BaseResponse;
import com.example.assessmentksp.helpers.DateFormatter;
import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.DepositHistory;
import com.example.assessmentksp.repository.DepositHistoryRepository;
import com.example.assessmentksp.repository.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DepositService {
    private final DepositHistoryRepository depositHistoryRepository;
    private final DepositRepository depositRepository;

    @Autowired
    private DateFormatter dateFormatter;

    public ResponseEntity<Object> getDepositById(Long id) {
        Deposit deposit = depositRepository.findByDepositId(id);
        String messages = (deposit != null)
                ? String.format("Show deposit by id: %s", id)
                : String.format("Deposit with id: %s not found!", id);
        return BaseResponse.successResponse(messages, HttpStatus.OK, deposit);
    }

    public ResponseEntity<Object> getAllDeposit(String memberId) {
        Object deposit;

        if (memberId != null) {
            deposit = depositRepository.findByMemberId(Long.parseLong(memberId));
        } else {
            deposit = depositRepository.findAllByOrderByCreatedAtDesc();
        }

        return BaseResponse.successResponse("Show all deposit", HttpStatus.OK, deposit);
    }

    public ResponseEntity<Object> createTransactional(DepositHistoryDto request) {
        String message = "";
        Deposit deposit = depositRepository.findByMemberId(Long.parseLong(request.getMemberId()));
        if (deposit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Deposit with member_id %s not found", request.getMemberId()));
        }

        LocalDate trxDate = dateFormatter.trxDateFormatter(request.getTrxDate());

        switch (request.getTrxType()) {
            case TrxType.TAKE:
                if (deposit.getBalance() - request.getTaken() <= 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Not enough funds to withdraw deposit, remaining balance Rp.%s", deposit.getBalance()));
                }
                Integer remainingTaken = deposit.getBalance() - request.getTaken();
                String historyTake = String.format(
                        "%s %s melakukan pengambilan uang sebesar Rp.%s pada tanggal %s",
                        deposit.getMember().getFirstName(), deposit.getMember().getLastName(), request.getTaken(), request.getTrxDate()
                );
                DepositHistory depositHistoryTaken = new DepositHistory(
                        request.getTaken(), deposit.getBalance(), remainingTaken, trxDate, TrxType.TAKE, historyTake
                );
                depositHistoryTaken.setMember(deposit.getMember());
                deposit.setBalance(remainingTaken);

                depositHistoryRepository.save(depositHistoryTaken);
                depositRepository.save(deposit);
                message = String.format("Successfully took funds of Rp.%s", request.getTaken());
                break;
            case TrxType.ADD:
                Integer remainingAdd = deposit.getBalance() + request.getTaken();
                String historyAdd = String.format(
                        "%s %s melakukan penyerahan uang sebesar Rp.%s pada tanggal %s",
                        deposit.getMember().getFirstName(), deposit.getMember().getLastName(), request.getTaken(), request.getTrxDate()
                );
                DepositHistory depositHistoryAdd = new DepositHistory(
                        request.getTaken(), deposit.getBalance(), remainingAdd, trxDate, TrxType.ADD, historyAdd
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

    public ResponseEntity<Object> getAllDepositHistories() {
        List<DepositHistory> deposit = depositHistoryRepository.findAllByOrderByCreatedAtDesc();
        return BaseResponse.successResponse(String.format("Show deposit histories summary"), HttpStatus.OK, deposit);
    }
}
