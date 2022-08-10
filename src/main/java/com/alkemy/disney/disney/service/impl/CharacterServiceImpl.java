package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Transactional
    public CharacterDTO save(CharacterDTO dto) throws ServiceError {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO resultDTO = characterMapper.characterEntity2DTO(entitySaved, true);
        return resultDTO;
    }

    @Transactional
    public CharacterDTO update(CharacterDTO dto) throws ServiceError {
        if(dto.getId()==null){
            throw new ServiceError("id field empty");
        }else {
            CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
            CharacterEntity entitySaved = characterRepository.save(entity);
            CharacterDTO resultDTO = characterMapper.characterEntity2DTO(entitySaved, true);
            return resultDTO;
        }
    }

    public List<CharacterDTO> getAll(){
        List<CharacterDTO> characters = characterMapper.characterListEntity2DTO(characterRepository.findAll(), true);
        return characters;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }
}
