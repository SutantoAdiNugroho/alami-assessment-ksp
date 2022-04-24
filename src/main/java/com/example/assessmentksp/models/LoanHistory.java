package com.example.assessmentksp.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_histories")
public class LoanHistory {
    @Id
    @GeneratedValue(generator = "loan_history_generator")
    @SequenceGenerator(
            name = "loan_history_generator",
            sequenceName = "loan_history_sequence",
            initialValue = 1000
    )
    private Integer id;

    @Column(name = "taken", nullable = false)
    private Long taken;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "payment_count", nullable = false)
    private Integer paymentCount;

    @Column(name = "remaining_amt", nullable = false)
    private Integer remainingAmount;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
