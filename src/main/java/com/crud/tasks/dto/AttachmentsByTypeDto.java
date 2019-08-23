package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentsByTypeDto {

    @JsonProperty("trello")
    private  TrelloDto trello ;

}
