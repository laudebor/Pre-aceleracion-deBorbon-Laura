package com.alkemy.disney.disney.entity;


import com.alkemy.disney.disney.enums.Score;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Score score;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE
            }
    )
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "character_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<CharacterEntity> characters = new HashSet();

}
