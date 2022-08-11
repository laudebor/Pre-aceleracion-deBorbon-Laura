package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.error.ServiceError;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto) throws ServiceError;

    MovieDTO update(MovieDTO dto) throws ServiceError;

    List<MovieDTO> getAll();

    void delete(Long id);

    List<MovieDTO> getByFilters(String name, Long genre, String order);
}
