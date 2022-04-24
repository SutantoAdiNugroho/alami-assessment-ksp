package com.example.assessmentksp.dto.loan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class GetLoansDto {
    private final Long memberId;
    private final String fromCreatedDate;
    private final String toCreatedDate;
}
