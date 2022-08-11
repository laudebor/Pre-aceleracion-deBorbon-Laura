package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.error.ServiceError;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto) throws ServiceError;

    MovieDTO update(MovieDTO dto) throws ServiceError;

    List<MovieDTO> getAll();

    void delete(Long id);

    List<MovieBasicDTO> getByFilters(String name, Long genre, String order);

    void addCharacter(Long movieId, Long characterId) throws ServiceError;

    public void removeCharacter(Long movieId, Long characterId) throws ServiceError;
}
