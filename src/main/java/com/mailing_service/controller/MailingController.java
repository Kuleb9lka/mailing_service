package com.mailing_service.controller;

import com.mailing_service.dto.MailDto;
import com.mailing_service.service.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailing")
@RequiredArgsConstructor
public class MailingController {

    private final MailingService mailingService;

    @PostMapping("/registration")
    public void checkEmailByRegistrationMail(@RequestBody MailDto mailDto){

        mailingService.sendMail(mailDto);
    }
}
