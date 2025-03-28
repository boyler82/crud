package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void shouldMapToBoards() {
//        GIVEN
        List<TrelloListDto> lists = List.of(
                new TrelloListDto("11","List11",true),
                new TrelloListDto("12","List12",false)
        );

        List<TrelloBoardDto> trelloBoardDtos = List.of(
                new TrelloBoardDto("1","Board1",lists),
                new TrelloBoardDto("2","Board2",lists)
        );

//        WHEN
        List<TrelloBoard> mappedBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        TrelloBoard mappedBoard = mappedBoards.stream()
                .filter(b-> b.getId().equals("2"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Not found board"));

        TrelloList mappedList = mappedBoards.stream()
                .flatMap(board -> board.getLists().stream())
                .filter(list -> list.isClosed())
                .findFirst()
                .orElseThrow(() -> new AssertionError("No closed list found"));
//        THEN
        assertEquals(2, mappedBoards.size());
        assertEquals("Board2", mappedBoard.getName());
        assertEquals("11", mappedList.getId());
        assertEquals("List11", mappedList.getName());
        assertTrue(mappedList.isClosed());
    }

    @Test
    void shouldMapToBoardsDto() {
//        given
        List<TrelloList> trelloLists = List.of(
                new TrelloList("12", "List12", true)
        );
        List<TrelloBoard> trelloBoards =List.of(
                new TrelloBoard("22","Board22",trelloLists)
        );
//        when

        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloBoardDto result = trelloBoardsDto.get(0);

//        then
        assertEquals(1,trelloBoardsDto.size());
        assertEquals("Board22", result.getName());
        assertEquals(1,result.getLists().size());
        assertEquals(true,result.getLists().get(0).isClosed());
    }

    @Test
    void shouldMapToListDto() {
        // given
        List<TrelloList> lists = List.of(
                new TrelloList("55", "List55", true)
        );

        // when
        List<TrelloListDto> result = trelloMapper.mapToListDto(lists);

        // then
        assertEquals(1, result.size());
        TrelloListDto listDto = result.get(0);
        assertEquals("55", listDto.getId());
        assertEquals("List55", listDto.getName());
        assertTrue(listDto.isClosed());
    }

    @Test
    void shouldMapToList() {
        // given
        List<TrelloListDto> listDtos = List.of(
                new TrelloListDto("99", "List99", false)
        );

        // when
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);

        // then
        assertEquals(1, lists.size());
        TrelloList list = lists.get(0);
        assertEquals("99", list.getId());
        assertEquals("List99", list.getName());
        assertFalse(list.isClosed());
    }

    @Test
    void shouldMapToCardDto() {
        // given
        TrelloCard card = new TrelloCard("Task1", "Description1", "top", "123");

        // when
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        // then
        assertEquals("Task1", cardDto.getName());
        assertEquals("Description1", cardDto.getDescription());
        assertEquals("top", cardDto.getPos());
        assertEquals("123", cardDto.getListId());
    }

    @Test
    void shouldMapToCard() {
        // given
        TrelloCardDto cardDto = new TrelloCardDto("CardName", "Desc", "bottom", "456");

        // when
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        // then
        assertEquals("CardName", card.getName());
        assertEquals("Desc", card.getDescription());
        assertEquals("bottom", card.getPos());
        assertEquals("456", card.getListId());
    }

}