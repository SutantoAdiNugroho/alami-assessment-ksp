package com.example.assessmentksp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "deposits")
@NoArgsConstructor
public class Deposit implements Serializable {
    @Id
    @GeneratedValue(generator = "deposit_generator")
    @SequenceGenerator(
            name = "deposit_generator",
            sequenceName = "deposit_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "member_id" )
    private Member member;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Deposit(Integer balance) {
        this.balance = balance;
    }
}
