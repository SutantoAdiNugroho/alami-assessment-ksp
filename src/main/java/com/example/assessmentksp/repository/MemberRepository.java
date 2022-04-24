package com.example.assessmentksp.repository;

import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("FROM Member m WHERE m.id = :memberId")
    Member findByMemberId(@Param("memberId") Long memberId);

    List<Member> findAllByOrderByCreatedAtDesc();
}
