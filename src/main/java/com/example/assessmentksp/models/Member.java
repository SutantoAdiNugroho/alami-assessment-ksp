package com.example.assessmentksp.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 50)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "address", nullable = false)
    @Size(max = 100)
    private String address;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "member")
    private Deposit deposit;

    @OneToMany(mappedBy = "member")
    private List<DepositHistory> depositHistories;

    @OneToMany(mappedBy = "member")
    private List<Loan> loans;

    @OneToMany(mappedBy = "member")
    private List<LoanHistory> loanHistories;
}
