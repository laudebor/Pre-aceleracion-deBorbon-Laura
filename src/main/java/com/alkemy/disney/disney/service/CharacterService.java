package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.error.ServiceError;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto) throws ServiceError;
    CharacterDTO update(CharacterDTO dto) throws ServiceError;

    List<CharacterDTO> getAll();
    void delete(Long id);

    List<CharacterDTO> getByFilters(String name, Long age, Set<Long> movies, String order);


}
