package com.mailing_service.consumer;

import com.mailing_service.dto.MailDto;
import com.mailing_service.service.MailingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailingConsumer {

    private final MailingService mailingService;

    @KafkaListener(topics = "mailing", groupId = "mailing-group")
    public void consume(@Payload MailDto message,
                        Acknowledgment acknowledgement) {

        try {
            log.info("Message received: " + message);

            mailingService.sendMail(message);
            log.info("Message sent to {}", message.getEmail());

            acknowledgement.acknowledge();
            log.info("Acknowledgement sent.");
        } catch (Exception e) {

            log.error("Sending message exception: {}", e.getMessage(), e);
            throw e;
        }
    }
}