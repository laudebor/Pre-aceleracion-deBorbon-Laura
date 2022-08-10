package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private MovieRepository movieRepository;

    public MovieEntity movieDTO2Entity(MovieDTO dto) throws ServiceError {
        MovieEntity entity;
        if(dto.getId()==null){
            entity = new MovieEntity();
            entity.setImage(dto.getImage());
            entity.setTitle(dto.getTitle());
            entity.setCreationDate(dto.getCreationDate());
            entity.setScore(dto.getScore());
            entity.setGenreId(dto.getGenreId());
            entity.setCharacters(characterMapper.characterDTOList2EntitySet(dto.getCharacters()));
            return entity;
        }else {
            Optional<MovieEntity> result = movieRepository.findById(dto.getId());
            if (result.isPresent()) {
                entity = result.get();
                return entity;
            } else {
                throw new ServiceError("id not found");
            }
        }

    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setScore(entity.getScore());
        dto.setGenreId(entity.getGenreId());
        if(loadCharacters){
            List<CharacterDTO> characters = characterMapper.characterSetEntity2DTO(entity.getCharacters(), false);
            dto.setCharacters(characters);
        }
        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for(MovieEntity aux : entities){
            dtos.add(movieEntity2DTO(aux, loadCharacters));
        }
        return dtos;
    }


}
