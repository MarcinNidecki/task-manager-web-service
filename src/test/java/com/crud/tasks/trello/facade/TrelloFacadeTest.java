package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.CreatedTrelloCardDto;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1","test",trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToTrelloBoardList(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToTrelloBoardDtoList(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(0,trelloBoardDtoList.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_lists", false));

        List<TrelloBoard> mappdedTrelloBoards = new ArrayList<>();
        mappdedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToTrelloBoardList(trelloBoards)).thenReturn(mappdedTrelloBoards);
        when(trelloMapper.mapToTrelloBoardDtoList(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappdedTrelloBoards)).thenReturn(mappdedTrelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloBoardDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void createCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "1", "2");
        TrelloCard trelloCard = new TrelloCard("name","desc","2","2");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","name","http://test.com");
        //When
        when(trelloMapper.mapToTrelloCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToTrelloCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //Then
        CreatedTrelloCardDto createCard = trelloFacade.createCard(trelloCardDto);
        assertEquals(createdTrelloCardDto,createCard);
    }
}