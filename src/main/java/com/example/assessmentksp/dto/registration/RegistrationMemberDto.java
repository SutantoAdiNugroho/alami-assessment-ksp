package com.example.assessmentksp.dto.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationMemberDto {
    @NotEmpty
    @Size(min = 3)
    private final String firstName;

    @NotEmpty
    @Size(min = 3)
    private final String lastName;

    @NotEmpty
    private final String dob;

    @NotEmpty
    private final String address;
}
