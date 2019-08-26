package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard =  new TrelloBoard("1","test", new ArrayList<>());
        List<TrelloBoard> listOfTrelloBoard = new ArrayList<>();
        listOfTrelloBoard.add(trelloBoard);
        //Whne
        List<TrelloBoard>  validatedBoasrd =  trelloValidator.validateTrelloBoards(listOfTrelloBoard);
        //Then
        assertEquals(0,validatedBoasrd.size());
    }
}