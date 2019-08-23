package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {

    public TrelloBoard mapToTrelloBoard(final TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(
                trelloBoardDto.getId(),
                trelloBoardDto.getName(),
                mapToListOfTrelloList(trelloBoardDto.getLists()));
    }
    public TrelloBoardDto mapToTrelloBoardDto (final TrelloBoard trelloBoard) {
        return new TrelloBoardDto(
                trelloBoard.getId(),
                trelloBoard.getName(),
                mapToListOfTrelloListDto(trelloBoard.getLists()));
    }

    public TrelloList mapToTrelloList(final TrelloListDto trelloListDto ) {
        return new TrelloList(
                trelloListDto.getId(),
                trelloListDto.getName(),
                trelloListDto.isClosed());
    }
    public TrelloListDto mapToTrelloListDto(final TrelloList trelloList ) {
        return new TrelloListDto(
                trelloList.getId(),
                trelloList.getName(),
                trelloList.isClosed());
    }

    public List<TrelloList> mapToListOfTrelloList(final List<TrelloListDto> trelloListsDto) {
        return  trelloListsDto.stream()
                .map(this::mapToTrelloList)
                .collect(toList());
    }

    public List<TrelloListDto> mapToListOfTrelloListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(this::mapToTrelloListDto)
                .collect(toList());
    }

    public List<TrelloBoard> mapToTrelloBoardList(final List<TrelloBoardDto> trelloBoardDtoList) {
        return trelloBoardDtoList.stream()
                .map(this::mapToTrelloBoard)
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToTrelloBoardDtoList(final List<TrelloBoard> trelloBoardsList) {
        return trelloBoardsList.stream()
                .map(this::mapToTrelloBoardDto)
                .collect(toList());
    }

    public TrelloCardDto mapToTrelloCardDto(final TrelloCard trelloCard) {
        return  new TrelloCardDto(trelloCard.getName(),trelloCard.getDescription(),trelloCard.getPos(),trelloCard.getListId());
    }

    public TrelloCard mapToTrelloCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(),trelloCardDto.getDescription(),trelloCardDto.getPos(),trelloCardDto.getListId());
    }
}
