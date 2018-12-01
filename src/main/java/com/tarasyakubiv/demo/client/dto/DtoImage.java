package com.tarasyakubiv.demo.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoImage {
    @NonNull
    private String image;
    @NonNull
    private String thumb = "";
    @NonNull
    private String name = "";
    @NonNull
    private String sourceLink = "";
}