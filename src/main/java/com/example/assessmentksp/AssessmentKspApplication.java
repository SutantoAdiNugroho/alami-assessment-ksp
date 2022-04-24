package com.example.assessmentksp;

import com.example.assessmentksp.repository.DepositRepository;
import com.example.assessmentksp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AssessmentKspApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessmentKspApplication.class, args);
    }

}
