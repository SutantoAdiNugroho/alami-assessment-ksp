package com.example.assessmentksp.models;

import com.example.assessmentksp.constants.TrxType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deposit_history")
public class DepositHistory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "taken", nullable = false)
    private Integer taken;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "previous_balance", nullable = false)
    private Integer previousBalance;

    @Column(name = "remaining_balance", nullable = false)
    private Integer remainingBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false)
    private TrxType trxType;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
