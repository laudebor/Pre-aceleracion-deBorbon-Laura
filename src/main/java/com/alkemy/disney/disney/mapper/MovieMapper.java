package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
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
            entity.setCharacters(characterMapper.characterDTOList2EntitySetMovieCreation(dto.getCharacters()));
            return entity;
        }else {
            Optional<MovieEntity> result = movieRepository.findById(dto.getId());
            if (result.isPresent()) {
                entity = result.get();
                entity.setCharacters(characterMapper.characterDTOList2EntitySetMovieCreation(dto.getCharacters()));
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

    public MovieEntity movieDTO2EntityUpdate(MovieDTO dto) throws ServiceError {
        Optional<MovieEntity> result = movieRepository.findById(dto.getId());
        if (result.isPresent()) {
            MovieEntity entity = result.get();
            entity.setImage(dto.getImage());
            entity.setTitle(dto.getTitle());
            entity.setCreationDate(dto.getCreationDate());
            entity.setScore(dto.getScore());
            entity.setGenreId(dto.getGenreId());
            entity.setCharacters(entity.getCharacters());
            return entity;
            } else {
                throw new ServiceError("id not found");
            }
        }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for(MovieEntity aux : entities){
            dtos.add(movieEntity2DTO(aux, loadCharacters));
        }
        return dtos;
    }

    public MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity){
        MovieBasicDTO basicDTO = new MovieBasicDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setTitle(entity.getTitle());
        basicDTO.setCreationDate(entity.getCreationDate());
        return basicDTO;
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTO(List<MovieEntity> entities){
        List<MovieBasicDTO> basicDTOs = new ArrayList<>();
        for(MovieEntity aux : entities){
            basicDTOs.add(movieEntity2BasicDTO(aux));
        }
        return basicDTOs;
    }


}
