package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.DepositHistory;
import com.example.assessmentksp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositHistoryRepository extends JpaRepository<DepositHistory, Long>, JpaSpecificationExecutor<DepositHistory> {
    List<DepositHistory> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);

    List<DepositHistory> findAllByOrderByCreatedAtDesc();
}
