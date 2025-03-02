package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler; // Testowana klasa

    @Mock
    private SimpleEmailService simpleEmailService; // Mockujemy wysyłanie e-maili

    @Mock
    private TaskRepository taskRepository; // Mockujemy bazę danych

    @Mock
    private AdminConfig adminConfig; // Mockujemy admina (adres e-mail)

    private static final String ADMIN_EMAIL = "admin@example.com";

    @BeforeEach
    void setUp() {
        when(adminConfig.getAdminMail()).thenReturn(ADMIN_EMAIL);
    }

    @Test
    void shouldSendEmailWithSingularTask() {
        // Given
        when(taskRepository.count()).thenReturn(1L);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(simpleEmailService, times(1)).send(mailCaptor.capture());

        Mail capturedMail = mailCaptor.getValue();
        assertEquals(ADMIN_EMAIL, capturedMail.getMailTo());
        assertEquals("Tasks: Once a day email", capturedMail.getSubject());
        assertEquals("Currently in database you got: 1 task", capturedMail.getMessage());
    }

    @Test
    void shouldSendEmailWithPluralTasks() {
        // Given
        when(taskRepository.count()).thenReturn(5L);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(simpleEmailService, times(1)).send(mailCaptor.capture());

        Mail capturedMail = mailCaptor.getValue();
        assertEquals(ADMIN_EMAIL, capturedMail.getMailTo());
        assertEquals("Tasks: Once a day email", capturedMail.getSubject());
        assertEquals("Currently in database you got: 5 tasks", capturedMail.getMessage());
    }
}