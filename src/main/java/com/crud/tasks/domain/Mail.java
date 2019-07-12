package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {
    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;

}

