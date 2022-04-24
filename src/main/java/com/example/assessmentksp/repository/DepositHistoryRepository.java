package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.DepositHistory;
import com.example.assessmentksp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositHistoryRepository extends JpaRepository<DepositHistory, Long> {
    List<DepositHistory> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
}
