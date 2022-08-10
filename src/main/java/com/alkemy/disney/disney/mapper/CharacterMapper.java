package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) throws ServiceError {
        CharacterEntity entity;
        if(dto.getId()==null){
            entity = new CharacterEntity();
            entity.setImage(dto.getImage());
            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setStory(dto.getStory());
            //TODO: movieDTO2Entity entity.setMovies(dto.getMovies());
            // QUIZAS NO HAGA FALTA, CUANDO CARGO UN PERSONAJE NO HACE FALTA CARGAR LAS PELICULAS
            // PODEMOS HACER LA RELACION DESDE PELICULA CUANDO CARGAMOS UNA PELICULA, ASOCIAMOS LOS PERSONAJES
            return entity;
        }else{
            Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
            if(result.isPresent()){
                entity = result.get();
                entity.setImage(dto.getImage());
                entity.setName(dto.getName());
                entity.setAge(dto.getAge());
                entity.setWeight(dto.getWeight());
                entity.setStory(dto.getStory());
                return entity;
            }else{
                throw new ServiceError("id not found");
            }
        }

    }

    public CharacterEntity characterDTO2EntityMovieCreation(CharacterDTO dto) throws ServiceError {
        CharacterEntity entity;
        if(dto.getId()==null){
            entity = new CharacterEntity();
            entity.setImage(dto.getImage());
            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setStory(dto.getStory());
            return entity;
        }else{
            Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
            if(result.isPresent()){
                entity = result.get();
                return entity;
            }else{
                throw new ServiceError("id not found");
            }
        }

    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if(loadMovies){
            List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(movies);
        }
        return dto;
    }

    public List<CharacterDTO> characterSetEntity2DTO(Set<CharacterEntity> entities, boolean loadMovies){
        List<CharacterDTO> dtos = new ArrayList<>();
        for(CharacterEntity aux : entities){
            dtos.add(characterEntity2DTO(aux, loadMovies));
        }
        return dtos;
    }

    public List<CharacterDTO> characterListEntity2DTO(List<CharacterEntity> entities, boolean loadMovies){
        List<CharacterDTO> dtos = new ArrayList<>();
        for(CharacterEntity aux : entities){
            dtos.add(characterEntity2DTO(aux, loadMovies));
        }
        return dtos;
    }

    public Set<CharacterEntity> characterDTOList2EntitySet(List<CharacterDTO> dtos) throws ServiceError {
        Set<CharacterEntity> entities = new HashSet<>();
        for(CharacterDTO aux : dtos){
            entities.add(characterDTO2Entity(aux));
        }
        return entities;
    }

    public Set<CharacterEntity> characterDTOList2EntitySetMovieCreation(List<CharacterDTO> dtos) throws ServiceError {
        Set<CharacterEntity> entities = new HashSet<>();
        for(CharacterDTO aux : dtos){
            entities.add(characterDTO2EntityMovieCreation(aux));
        }
        return entities;
    }

}
