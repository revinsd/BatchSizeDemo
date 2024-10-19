package com.example.BatchSizePower.mapper;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MainEntityDto {
    private long id;
    private String name;
    private List<SubEntityDto> sub1;
    private List<SubEntityDto> sub2;
    private List<SubEntityDto> sub3;
    private List<SubEntityDto> sub4;
    private List<SubEntityDto> sub5;
}
