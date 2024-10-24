package com.example.BatchSizePower.mapper;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SubEntityDto {
    private long id;
    private String name;
    private List<SubSubEntityDto> sub1;
    private List<SubSubEntityDto> sub2;
}
