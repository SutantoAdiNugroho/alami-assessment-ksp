package com.example.assessmentksp.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "loan_histories")
@NoArgsConstructor
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
    private Integer taken;

    @JsonIncludeProperties({"id", "firstName", "lastName"})
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "remaining_amt", nullable = false)
    private Integer remainingAmount;

    @JsonIncludeProperties({"id", "status"})
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Size(max = 7)
    @Column(name = "status", nullable = false)
    private String loanStatus;

    @Column(name = "trx_date", nullable = false)
    private LocalDate trxDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public LoanHistory(Integer taken, Integer remainingAmount, String loanStatus, LocalDate trxDate, String description) {
        this.taken = taken;
        this.remainingAmount = remainingAmount;
        this.trxDate = trxDate;
        this.loanStatus = loanStatus;
        this.description = description;
    }
}
