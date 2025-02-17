package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TasksDto {

    private Long id;
    private String title;
    private String content;
}
