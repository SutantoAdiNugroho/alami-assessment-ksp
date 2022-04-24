package com.example.assessmentksp.services;

import com.example.assessmentksp.dto.registration.RegistrationMemberDto;
import com.example.assessmentksp.dto.response.BaseResponse;
import com.example.assessmentksp.models.Deposit;
import com.example.assessmentksp.models.Member;
import com.example.assessmentksp.repository.DepositRepository;
import com.example.assessmentksp.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final DepositRepository depositRepository;

    public ResponseEntity<Object> register(RegistrationMemberDto request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateDob = LocalDate.parse(request.getDob(), formatter);

        // Automatically create simpanan with balance 0
        Deposit deposit = new Deposit(0);
        Member member = new Member(request.getFirstName(), request.getLastName(), dateDob, request.getAddress());
        deposit.setMember(member);
        member.setDeposit(deposit);

        depositRepository.save(deposit);
        memberRepository.save(member);

        Map<String, Object> mapReturn = new HashMap<String, Object>();
        mapReturn.put("firstName", request.getFirstName());
        mapReturn.put("lastName", request.getLastName());
        mapReturn.put("address", request.getAddress());

        return BaseResponse.successResponse("Member successfully created", HttpStatus.CREATED, mapReturn);
    }

    public ResponseEntity<Object> showMembers() {
        List<Member> members = memberRepository.findAllByOrderByCreatedAtDesc();
        String messages = members.size() > 0 ? "Show all members" : "Members is none";
        return BaseResponse.successResponse(messages, HttpStatus.OK, members);
    }
}
