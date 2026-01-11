package com.mailing_service.service;

import com.mailing_service.dto.MailDto;

public interface MailingService {

    void sendMail(MailDto dto);
}
