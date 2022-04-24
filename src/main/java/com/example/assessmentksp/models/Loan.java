package com.example.assessmentksp.models;

import com.example.assessmentksp.constants.LoanStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(generator = "loan_generator")
    @SequenceGenerator(
            name = "loan_generator",
            sequenceName = "loan_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "payment_count", nullable = false)
    private Integer paymentCount;

    @Column(name = "installment_amt", nullable = false)
    private Integer installmentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus loanStatus;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
