package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

public interface GenreService {

    GenreDTO save(GenreDTO dto) throws ParamNotFound;

}
