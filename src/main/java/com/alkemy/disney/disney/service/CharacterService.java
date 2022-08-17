package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto) throws ParamNotFound;
    CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound;

    List<CharacterDTO> getAll();

    public CharacterDTO getById(Long id);
    void delete(Long id);

    List<CharacterBasicDTO> getByFilters(String name, String age, Set<Long> movies, String order);


}
