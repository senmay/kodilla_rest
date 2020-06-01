package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("5", "List5", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("6", "List6", false);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);
        //when
        List<TrelloList> trelloLists = trelloMapper.mapToLists(trelloListsDto);
        //then
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("5", trelloLists.get(0).getId());
        Assert.assertEquals("List6", trelloLists.get(1).getName());
    }

    @Test
    public void testMapToListDto() {
        //given
        TrelloList trelloList1 = new TrelloList("7", "List7", true);
        TrelloList trelloList2 = new TrelloList("8", "List8", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListsDto(trelloLists);
        //then
        Assert.assertEquals(2, trelloListsDto.size());
        Assert.assertEquals("7", trelloListsDto.get(0).getId());
        Assert.assertEquals("List8", trelloListsDto.get(1).getName());
    }

    @Test
    public void testMapToBoard() {
        TrelloListDto trelloListDto1 = new TrelloListDto("3", "List3", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("4", "List4", false);
        List<TrelloListDto> trelloLists = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Board1", trelloLists);
        List<TrelloBoardDto> trelloBoardListDto = Arrays.asList(trelloBoardDto);
        //when
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardListDto);
        //then
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals(2, trelloBoardList.get(0).getLists().size());
        Assert.assertEquals("3", trelloBoardList.get(0).getLists().get(0).getId());
        Assert.assertEquals("List3", trelloBoardList.get(0).getLists().get(0).getName());
    }

    @Test
    public void testMapToBoardDto() {
        //given
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        TrelloList trelloList2 = new TrelloList("2", "List2", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board1", trelloLists);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);
        //when
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //then
        Assert.assertEquals(1, trelloBoardDtoList.size());
        Assert.assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        Assert.assertEquals("1", trelloBoardDtoList.get(0).getLists().get(0).getId());
        Assert.assertEquals("List1", trelloBoardDtoList.get(0).getLists().get(0).getName());
    }

    @Test
    public void testMapToCard() {
        //given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("Card1", "description1", "1", "1");
        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto1);
        //then
        Assert.assertEquals("Card1", trelloCard.getName());
        Assert.assertEquals("description1", trelloCard.getDescription());
        Assert.assertEquals("1", trelloCard.getPos());
        Assert.assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //given
        TrelloCard trelloCard1 = new TrelloCard("Card1", "description1", "1", "1");
        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard1);
        //then
        Assert.assertEquals("Card1", trelloCardDto.getName());
        Assert.assertEquals("description1", trelloCardDto.getDescription());
        Assert.assertEquals("1", trelloCardDto.getPos());
        Assert.assertEquals("1", trelloCardDto.getListId());
    }
}