package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.error.ServiceError;

public interface MovieService {

    MovieDTO save(MovieDTO dto) throws ServiceError;
}
