package com.example.BatchSizePower.mapper;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubSubEntityDto {
    private long id;
    private String name;
}
