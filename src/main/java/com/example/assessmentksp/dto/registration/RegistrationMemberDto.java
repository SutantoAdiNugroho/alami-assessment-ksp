package com.example.assessmentksp.dto.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationMemberDto {
    @NotEmpty
    private final String firstName;

    @NotEmpty
    private final String lastName;

    @NotEmpty
        private final String dob;

    @NotEmpty
    private final String address;
}
