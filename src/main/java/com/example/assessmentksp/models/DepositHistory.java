package com.example.assessmentksp.models;

import com.example.assessmentksp.constants.TrxType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "deposit_histories")
@NoArgsConstructor
public class DepositHistory {
    @Id
    @GeneratedValue(generator = "deposit_history_generator")
    @SequenceGenerator(
            name = "deposit_history_generator",
            sequenceName = "deposit_history_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "taken", nullable = false)
    private Integer taken;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "previous_balance", nullable = false)
    private Integer previousBalance;

    @Column(name = "remaining_balance", nullable = false)
    private Integer remainingBalance;

    @Size(max = 5)
    @Column(name = "trx_type", nullable = false)
    private String trxType;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public DepositHistory(Integer taken, Integer previousBalance, Integer remainingBalance, String trxType) {
        this.taken = taken;
        this.previousBalance = previousBalance;
        this.remainingBalance = remainingBalance;
        this.trxType = trxType.toString();
    }
}
