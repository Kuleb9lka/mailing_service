package com.mailing_service.service.impl;

import com.mailing_service.constant.ExceptionConstant;
import com.mailing_service.dto.MailDto;
import com.mailing_service.exception.EmailNotFoundException;
import com.mailing_service.service.MailingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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

        log.info("Constructed simple mail to {}", dto.getEmail());

        try {

            log.info("Tyring to send mail");

            mailSender.send(simpleMailMessage);

            log.info("Mail was successfully sent");

        } catch (MailSendException e) {

            log.error("Sending message exception {}", e.getMessage(), e);

            throw new EmailNotFoundException(ExceptionConstant.EMAIL_NOT_FOUND + dto.getEmail());
        }
    }
}
