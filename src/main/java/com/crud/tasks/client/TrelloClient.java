package com.crud.tasks.client;


import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private  String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUserName;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getUri(trelloApiEndpoint,trelloUserName,trelloAppKey,trelloToken);

        TrelloBoardDto[] boardsResponse =  restTemplate.getForObject(url, TrelloBoardDto[].class);
        return Optional.ofNullable(boardsResponse).map(Arrays::asList).orElseGet(ArrayList::new);

    }

    private URI getUri(String apiEndpoint, String apiUserName, String apiKey, String apiToken) {
        return UriComponentsBuilder.fromHttpUrl(apiEndpoint + "/members/" + apiUserName + "/boards")
                    .queryParam("key", apiKey)
                    .queryParam("token", apiToken)
                    .queryParam("username", apiUserName)
                    .queryParam("fields", "name,id").build().encode().toUri();
    }
}
