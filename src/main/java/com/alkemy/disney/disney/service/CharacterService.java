package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.error.ServiceError;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto) throws ServiceError;
    CharacterDTO update(CharacterDTO dto) throws ServiceError;

    List<CharacterDTO> getAll();
    void delete(Long id);


}
