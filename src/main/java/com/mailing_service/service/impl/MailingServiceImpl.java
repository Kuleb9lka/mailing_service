package com.mailing_service.service.impl;

import com.mailing_service.dto.MailDto;
import com.mailing_service.service.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingServiceImpl implements MailingService {

    private final JavaMailSender mailSender;

    @Value("${spring.,ail.sender.email}")
    private String senderMail;

    @Override
    public void sendMail(MailDto dto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(senderMail);
        simpleMailMessage.setTo(dto.getEmail());
        simpleMailMessage.setSubject(dto.getTheme());
        simpleMailMessage.setText(dto.getText());

        mailSender.send(simpleMailMessage);
    }
}
