package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmailWithCc() {
        //Given
       Mail mailWithCC = Mail.builder()
               .mailTo("test@op.pl")
               .subject("test")
               .message("test")
               .toCc("test@op.pl")
               .build();


        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo("test@op.pl");
        expectedMessage.setSubject("test");
        expectedMessage.setText("test");
        expectedMessage.setCc("test@op.pl");

        // When
        simpleEmailService.send(mailWithCC);

        // Then
        verify(javaMailSender, times(1)).send(expectedMessage);
    }

    @Test
    public void shouldSendEmailWithoutCc() {
        //Given
        Mail mailWithCC = Mail.builder()
                .mailTo("test@op.pl")
                .subject("test")
                .message("test")
                .toCc(null)
                .build();


        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo("test@op.pl");
        expectedMessage.setSubject("test");
        expectedMessage.setText("test");

        // When
        simpleEmailService.send(mailWithCC);

        // Then
        verify(javaMailSender, times(1)).send(expectedMessage);
    }
}