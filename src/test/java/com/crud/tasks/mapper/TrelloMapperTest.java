package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    private TrelloBoard getTrelloBoard() {
        return  new TrelloBoard("1","trello board", getListOfTrelloList());
    }
    private TrelloBoardDto getTrelloBoardDto() {
        return  new TrelloBoardDto("1","trello board", getListOfTrelloListDto());
    }

    private TrelloList getTrelloList() {
        return new TrelloList("1", "name", true);
    }

    private TrelloListDto getTrelloListDto() {
        return new TrelloListDto("1", "name", true);
    }

    private List<TrelloListDto> getListOfTrelloListDto() {
        TrelloListDto trelloListDto1 = getTrelloListDto();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto1);
        return trelloListDtoList;
    }

    private List<TrelloList> getListOfTrelloList() {
        TrelloList trelloList1 = getTrelloList();
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloList1);
        return trelloListList;
    }

    private List<TrelloBoard> getListOfTrelloBoard() {
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(getTrelloBoard());
        return trelloBoardsList;
    }

    private List<TrelloBoardDto> getListOfTrelloBoardDto() {
        List<TrelloBoardDto> trelloBoardsListDto = new ArrayList<>();
        trelloBoardsListDto.add(getTrelloBoardDto());
        return trelloBoardsListDto;
    }

    @Test
    public void mapToTrelloBoard() {
        //given
        TrelloBoardDto trelloBoardDto = getTrelloBoardDto();
        //When
        TrelloBoard trelloBoard = trelloMapper.mapToTrelloBoard(trelloBoardDto);
        //Then
        assertEquals("1", trelloBoard.getId());
        assertEquals("trello board", trelloBoard.getName());
        assertEquals(1, trelloBoard.getLists().size());
    }

    @Test
    public void mapToTrelloBoardDto() {
        //given
        TrelloBoard trelloBoard = getTrelloBoard();
        //When
        TrelloBoardDto trelloBoardDto = trelloMapper.mapToTrelloBoardDto(trelloBoard);
        //Then
        assertEquals("1", trelloBoardDto.getId());
        assertEquals("trello board", trelloBoardDto.getName());
        assertEquals(1, trelloBoardDto.getLists().size());
    }

    @Test
    public void mapToListOfTrelloList() {
        //Given
        List<TrelloListDto> trelloListDtoList = getListOfTrelloListDto();
        //When
        List<TrelloList> trelloList = trelloMapper.mapToListOfTrelloList(trelloListDtoList);
        //Then
        assertEquals(1, trelloList.size());
    }

    @Test
    public void mapToListOfTrelloListDto() {
        //Given
        List<TrelloList> trelloLists = getListOfTrelloList();
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListOfTrelloListDto(trelloLists);
        //Then
        assertEquals(1, trelloListDto.size());
    }

    @Test
    public void mapToTrelloBoardList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoLists = getListOfTrelloBoardDto();
        //When
        List<TrelloBoard> trelloBoardsList = trelloMapper.mapToTrelloBoardList(trelloBoardDtoLists);
        //Then
        assertEquals(1,trelloBoardDtoLists.size());
    }

    @Test
    public void mapToTrelloBoardDtoList() {
        //Given
        List<TrelloBoard> trelloBoardList = getListOfTrelloBoard();
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToTrelloBoardDtoList(trelloBoardList);
        //Then
        assertEquals(1,trelloBoardDtoList.size());
    }

    @Test
    public void mapToTrelloList() {
        //Given
        TrelloList trelloList = getTrelloList();
        //When
        TrelloListDto trelloListDto = trelloMapper.mapToTrelloListDto(trelloList);
        //Then
        assertEquals("1", trelloListDto.getId());
        assertEquals("name", trelloListDto.getName());
        assertTrue(trelloListDto.isClosed());
    }

    @Test
    public void mapToTrelloListDto() {
        //Given
        TrelloListDto trelloListDto = getTrelloListDto();
        //When
        TrelloList trelloList = trelloMapper.mapToTrelloList(trelloListDto);
        //Then
        assertEquals("1", trelloList.getId());
        assertEquals("name", trelloList.getName());
        assertTrue(trelloList.isClosed());
    }

    @Test
    public void mapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card","new card","2","133");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        //Then
        assertEquals("Card", trelloCardDto.getName());
        assertEquals("new card", trelloCardDto.getDescription());
        assertEquals("2", trelloCardDto.getPos());
        assertEquals("133", trelloCardDto.getListId());
    }

    @Test
    public void mapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card","new card","2","133");
        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        //Then
        assertEquals("Card", trelloCard.getName());
        assertEquals("new card", trelloCard.getDescription());
        assertEquals("2", trelloCard.getPos());
        assertEquals("133", trelloCard.getListId());
    }
}