package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("FROM Loan l WHERE l.id = :id")
    Loan findByLoanId(@Param("id") Long id);

    @Query("FROM Loan l WHERE l.member.id = :memberId")
    Loan findByMemberId(@Param("memberId") Long memberId);

    List<Loan> findAllByOrderByCreatedAtDesc();
}
