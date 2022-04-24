package com.example.assessmentksp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member implements Serializable {
    @Id
    @GeneratedValue(generator = "member_generator")
    @SequenceGenerator(
            name = "member_generator",
            sequenceName = "member_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 50)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "address", nullable = false)
    @Size(max = 100)
    private String address;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToOne(mappedBy = "member")
    private Deposit deposit;

    public Member(String firstName, String lastName, LocalDate dob, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
    }
}
