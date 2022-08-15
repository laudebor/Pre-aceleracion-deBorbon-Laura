package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterSpecification characterSpecification;
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Transactional
    public CharacterDTO save(CharacterDTO dto) throws ParamNotFound {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO resultDTO = characterMapper.characterEntity2DTO(entitySaved, true);
        return resultDTO;
    }

    @Transactional
    public CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound {
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if(result.isPresent()){
            CharacterEntity entity = result.get();
            dto.setId(id);
            entity = characterMapper.characterDTO2Entity(dto);
            CharacterEntity entitySaved = characterRepository.save(entity);
            CharacterDTO resultDTO = characterMapper.characterEntity2DTO(entitySaved, true);
            return resultDTO;
        }else{
            throw new ParamNotFound("character id not found");
        }
    }

    public List<CharacterDTO> getAll(){
        List<CharacterDTO> characters = characterMapper.characterListEntity2DTO(characterRepository.findAll(), true);
        return characters;
    }

    public void delete(Long id) {
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if(result.isPresent()){
            characterRepository.deleteById(id);
        }else{
            throw new ParamNotFound("character id not found");
        }
    }

    public List<CharacterBasicDTO> getByFilters(String name, String age, Set<Long> movies, String order){
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies, order);
        List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        //List<CharacterDTO> dtos = characterMapper.characterListEntity2DTO(entities, true);
        List<CharacterBasicDTO> basicDTOs = this.characterMapper.characterEntityList2BasicDTO(entities);
        //return dtos;
        return basicDTOs;
    }
}
