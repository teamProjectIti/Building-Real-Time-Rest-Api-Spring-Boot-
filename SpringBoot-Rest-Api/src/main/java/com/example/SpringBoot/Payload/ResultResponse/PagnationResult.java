package com.example.SpringBoot.Payload.ResultResponse;

import lombok.Data;

import java.util.List;
@Data
public class PagnationResult<T> {
    private List<T> Content;
    private int PageNo;
    private int PageSiz;
    private long TotalElement;
    private int TotalPages;
    private boolean last;
}
