package com.bootcamp.demo.demo_sb_bcforum4.model;

import java.util.List;
import lombok.Getter;

@Getter
public class ResponseDTO<T> {
    private List<T> data;
    private String message;


    public ResponseDTO(List<T> data, String message) {
        this.data = data;
        this.message = message;
    }
  
}
