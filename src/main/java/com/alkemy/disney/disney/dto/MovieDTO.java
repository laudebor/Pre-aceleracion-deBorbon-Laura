package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.enums.Score;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDate creationDate;

    private Score score;

    private Long genreId;

    private List<CharacterDTO> characters;

}
