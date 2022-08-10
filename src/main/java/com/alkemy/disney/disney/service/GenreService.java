package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.error.ServiceError;

public interface GenreService {

    GenreDTO save(GenreDTO dto) throws ServiceError;

    void delete(Long id);
}
