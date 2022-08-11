package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicDTO {

    private String image;

    private String title;

    private LocalDate creationDate;
}
