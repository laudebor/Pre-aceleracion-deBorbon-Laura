package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto) throws ParamNotFound;

    MovieDTO update(Long id, MovieDTO dto) throws ParamNotFound;

    List<MovieDTO> getAll();

    void delete(Long id);

    List<MovieBasicDTO> getByFilters(String name, String genre, String order);

    void addCharacter(Long movieId, Long characterId) throws ParamNotFound;

    public void removeCharacter(Long movieId, Long characterId) throws ParamNotFound;
}
