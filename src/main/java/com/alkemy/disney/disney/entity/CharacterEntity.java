package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character")
@Getter
@Setter
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private Long age;

    private Long weight;

    private String story;

    private Boolean deleted;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.MERGE)
    private List<MovieEntity> movies = new ArrayList<>();


}
