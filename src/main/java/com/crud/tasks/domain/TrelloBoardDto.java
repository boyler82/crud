package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrelloBoardDto {
    private String id;
    private String name;
    private List<TrelloListDto> lists;


}
