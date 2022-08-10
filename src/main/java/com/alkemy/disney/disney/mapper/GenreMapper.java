package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenreMapper {

    @Autowired
    private GenreRepository genreRepository;

    public GenreEntity genreDTO2Entity(GenreDTO dto) throws ServiceError {
        GenreEntity entity;
        if(dto.getId()==null){
            entity = new GenreEntity();
            entity.setName(dto.getName());
            entity.setImage(dto.getImage());
            return entity;
        }else{
            Optional<GenreEntity> result = genreRepository.findById(dto.getId());
            if(result.isPresent()){
                entity = result.get();
                entity.setName(dto.getName());
                entity.setImage(dto.getImage());
                return entity;
            }else{
                throw new ServiceError("id not found");
            }
        }
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }
}
