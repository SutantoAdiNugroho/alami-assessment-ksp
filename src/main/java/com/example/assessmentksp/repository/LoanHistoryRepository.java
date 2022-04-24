package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.LoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long>, JpaSpecificationExecutor<LoanHistory> {
    List<LoanHistory> findAllByOrderByCreatedAtDesc();
}
