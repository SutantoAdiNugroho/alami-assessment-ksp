package com.example.assessmentksp.services;

import com.example.assessmentksp.constants.LoanStatus;
import com.example.assessmentksp.dto.loan.LoanPaymentDto;
import com.example.assessmentksp.dto.loan.RegistrationLoanDto;
import com.example.assessmentksp.dto.response.BaseResponse;
import com.example.assessmentksp.helpers.DateFormatter;
import com.example.assessmentksp.models.Loan;
import com.example.assessmentksp.models.LoanHistory;
import com.example.assessmentksp.models.Member;
import com.example.assessmentksp.repository.LoanHistoryRepository;
import com.example.assessmentksp.repository.LoanRepository;
import com.example.assessmentksp.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanHistoryRepository loanHistoryRepository;
    private final MemberRepository memberRepository;

    @Autowired
    private DateFormatter dateFormatter;

    public ResponseEntity<Object> getLoanById(Long id) {
        Loan loan = loanRepository.findByLoanId(id);
        String messages = (loan != null)
                ? String.format("Show loan by id: %s", id)
                : String.format("Loan with id: %s not found!", id);
        return BaseResponse.successResponse(messages, HttpStatus.OK, loan);
    }

    public ResponseEntity<Object> getAllLoan(String memberId) {
        Object loan;

        if (memberId != null) {
            loan = loanRepository.findByMemberId(Long.parseLong(memberId));
        } else {
            loan = loanRepository.findAllByOrderByCreatedAtDesc();
        }

        String messages = (loan != null)
                ? String.format("Show all loans")
                : String.format("Loan with member_id: %s not found!", memberId);

        return BaseResponse.successResponse(messages, HttpStatus.OK, loan);
    }

    public ResponseEntity<Object> createLoan(RegistrationLoanDto request) {
        Member member = memberRepository.findByMemberId(Long.parseLong(request.getMemberId()));
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Member with member_id %s not found", request.getMemberId()));
        }

        LocalDate trxDate = dateFormatter.trxDateFormatter(request.getTrxDate());
        String historyDescription = String.format(
                "%s %s melakukan peminjaman sebesar %s kali pada tanggal %s",
                member.getFirstName(), member.getLastName(), request.getAmount(), request.getTrxDate()
                );
        Loan loan = new Loan(request.getAmount(), LoanStatus.NOTPAID);
        LoanHistory loanHistory = new LoanHistory(request.getAmount(), loan.getAmount(), LoanStatus.INITIAL, trxDate, historyDescription);

        loan.setMember(member);
        loanHistory.setLoan(loan);
        loanHistory.setMember(member);

        loanRepository.save(loan);
        loanHistoryRepository.save(loanHistory);

        return BaseResponse.successResponse(
                String.format("Successfully create loan for member_id: %s", request.getMemberId()),
                HttpStatus.CREATED,
                "loan_funds"
        );
    }

    public ResponseEntity<Object> getAllLoanHistories() {
        List<LoanHistory> loanHistories = loanHistoryRepository.findAllByOrderByCreatedAtDesc();
        return BaseResponse.successResponse("Show all loans", HttpStatus.OK, loanHistories);
    }

    public ResponseEntity<Object> createLoanPayment(LoanPaymentDto request) {
        Loan loan = loanRepository.findByLoanId(Long.parseLong(request.getLoanId()));
        if (loan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Loan with id %s not found", request.getLoanId()));
        } else if (loan.getLoanStatus().equals(LoanStatus.PAIDOFF)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan with id %s is already paidoff");
        }

        Integer remainingLoan = loan.getAmount() - request.getAmount();
        LocalDate trxDate = dateFormatter.trxDateFormatter(request.getTrxDate());

        if (remainingLoan > 0) {
            String historyDescription = String.format(
                    "%s %s melakukan pembayaran pinjaman sebesar Rp.%s pada loan_id %s di tanggal %s",
                    loan.getMember().getFirstName(), loan.getMember().getLastName(), request.getAmount(), loan.getId(), request.getTrxDate()
            );
            LoanHistory loanHistory = new LoanHistory(request.getAmount(), remainingLoan, LoanStatus.NOTPAID, trxDate, historyDescription);
            loanHistory.setMember(loan.getMember());
            loanHistory.setLoan(loan);
            loan.setAmount(remainingLoan);

            loanHistoryRepository.save(loanHistory);
            loanRepository.save(loan);
        } else if (remainingLoan == 0) {
            String historyDescription = String.format(
                    "%s %s melakukan pelunasan pinjaman sebesar Rp.%s pada loan_id %s di tanggal %s",
                    loan.getMember().getFirstName(), loan.getMember().getLastName(), request.getAmount(), loan.getId(), request.getTrxDate()
            );
            LoanHistory loanHistory = new LoanHistory(request.getAmount(), remainingLoan, LoanStatus.PAIDOFF, trxDate, historyDescription);
            loanHistory.setMember(loan.getMember());
            loanHistory.setLoan(loan);

            loan.setAmount(remainingLoan);
            loan.setLoanStatus(LoanStatus.PAIDOFF);

            loanHistoryRepository.save(loanHistory);
            loanRepository.save(loan);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The amount of money paid exceeds the current debt");
        }
        return BaseResponse.successResponse("Save payments loans", HttpStatus.OK, "loans_funds");
    }
}
