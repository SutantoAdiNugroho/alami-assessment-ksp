package com.example.assessmentksp.dto.deposit;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class DepositHistoryDto {
    @NotNull
    @Min(1000)
    private final Integer taken;

    @NotEmpty
    private final String memberId;

    @NotEmpty
    @Pattern(regexp="^(ADD|TAKE)$", message="Invalid transaction type!")
    private final String trxType;
}
