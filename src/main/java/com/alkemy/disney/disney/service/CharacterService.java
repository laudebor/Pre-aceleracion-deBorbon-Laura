package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.error.ServiceError;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto) throws ServiceError;

}
