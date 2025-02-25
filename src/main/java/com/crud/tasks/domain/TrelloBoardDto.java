package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor lub samo @Data
@Data
public class TrelloBoardDto {
    private String name;
    private String id;
    private List<TrelloListDto> lists;



}
