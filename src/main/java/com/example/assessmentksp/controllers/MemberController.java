package com.example.assessmentksp.controllers;

import com.example.assessmentksp.dto.registration.RegistrationMemberDto;
import com.example.assessmentksp.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/registration")
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationMemberDto request) {
        return memberService.register(request);
    }

    @GetMapping("/members")
    public ResponseEntity<Object> showMembers() {
        return memberService.showMembers();
    }
}
