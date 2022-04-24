package com.example.assessmentksp.models;

import com.example.assessmentksp.constants.LoanStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "loans")
@NoArgsConstructor
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

    @JsonIncludeProperties({"id", "firstName", "lastName"})
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Size(max = 7)
    @Column(name = "status", nullable = false)
    private String loanStatus;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Loan(Integer amount, String loanStatus) {
        this.amount = amount;
        this.loanStatus = loanStatus;
    }
}
