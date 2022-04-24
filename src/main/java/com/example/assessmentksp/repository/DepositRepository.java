package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Query("FROM Deposit d WHERE d.member.id = :memberId")
    Deposit findByMemberId(@Param("memberId") Long memberId);
}

