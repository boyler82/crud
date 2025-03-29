package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {
    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    @Test
    void shouldFetchTrelloCard() {
//        GIVEN
        List<TrelloBoardDto> mockList = List.of(new TrelloBoardDto("1","Name1",List.of()));
        when(trelloClient.getTrelloBoards()).thenReturn(mockList);
//        WHEN
        List<TrelloBoardDto> result= trelloService.fetchTrelloBoards();
//        THEN
        assertEquals(1, result.size());
        assertEquals("Name1", result.get(0).getName());
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    void shouldCreateTrelloCard() {
//        GIVEN
        TrelloCardDto mockCard = new TrelloCardDto("Card name","desc","post","1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Card name", "http://trello.com/card/1");
        when(trelloClient.createNewCard(mockCard)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

//        WHEN
        CreatedTrelloCardDto result = trelloService.createTrelloCard(mockCard);
//        THEN
        assertEquals("1", result.getId());
        verify(trelloClient, times(1)).createNewCard(mockCard);
        verify(emailService, times(1)).send(any(Mail.class));

    }
}