package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        //When
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        assertEquals("https://api.trello.com/1", endpoint);
    }

    @Test
    public void getTrelloAppKey() {
        //When
        String key = trelloConfig.getTrelloAppKey();
        //Then
        assertEquals("a59ebbc320a43d4efac09f17b1830ac1", key);
    }

    @Test
    public void getTrelloToken() {
        //When
        String token = trelloConfig.getTrelloToken();
        //Then
        assertEquals("a70df576e6f29443d18583183492698c4240cac0801d95dcc718da449b2ba5fc", token);
    }

    @Test
    public void getTrelloUserName() {
        //When
        String userName = trelloConfig.getTrelloUserName();
        //Then
        assertEquals("marcinnidecki", userName);
    }
}
