package com.example.assessmentksp.dto.loan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationLoanDto {
    @NotNull
    @Min(1000)
    private final Integer amount;

    @NotEmpty
    private final String memberId;

    @NotEmpty
    private final String trxDate;
}
