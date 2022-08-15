package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private MovieMapper movieMapper;

    @Transactional
    public MovieDTO save(MovieDTO dto) throws ParamNotFound {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO resultDTO = movieMapper.movieEntity2DTO(entitySaved, true);
        return resultDTO;
    }

    @Transactional
    public MovieDTO update(Long id, MovieDTO dto) throws ParamNotFound {
        Optional<MovieEntity> result = movieRepository.findById(id);
        if(result.isPresent()){
            MovieEntity entity = result.get();
            dto.setId(id);
            entity = movieMapper.movieDTO2EntityUpdate(dto);
            MovieEntity entitySaved = movieRepository.save(entity);
            MovieDTO resultDTO = movieMapper.movieEntity2DTO(entitySaved, true);
            return resultDTO;
        }else{
            throw new ParamNotFound("movie id not found");
        }
    }

    public List<MovieDTO> getAll(){
        List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(movieRepository.findAll(), true);
        return movies;
    }

    public void delete(Long id){
        Optional<MovieEntity> result = movieRepository.findById(id);
        if(result.isPresent()){
            movieRepository.deleteById(id);
        }else{
            throw new ParamNotFound("movie id not found");
        }
    }

    public List<MovieBasicDTO> getByFilters(String name, String genre, String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = movieMapper.movieEntityList2BasicDTO(entities);
        return dtos;
    }

    public void addCharacter(Long movieId, Long characterId) throws ParamNotFound {
        Optional<MovieEntity> movieResult = movieRepository.findById(movieId);
        if(movieResult.isPresent()){
            MovieEntity movieEntity = movieResult.get();
            Optional<CharacterEntity> characterResult = characterRepository.findById(characterId);
            if(characterResult.isPresent()){
                CharacterEntity characterEntity = characterResult.get();
                movieEntity.getCharacters().add(characterEntity);
                movieRepository.save(movieEntity);
            }else{
                throw new ParamNotFound("character id not found");
            }
        }else{
            throw new ParamNotFound("movie id not found");
        }
    }

    public void removeCharacter(Long movieId, Long characterId) throws ParamNotFound {
        Optional<MovieEntity> movieResult = movieRepository.findById(movieId);
        if(movieResult.isPresent()){
            MovieEntity movieEntity = movieResult.get();
            Optional<CharacterEntity> characterResult = characterRepository.findById(characterId);
            if(characterResult.isPresent()){
                CharacterEntity characterEntity = characterResult.get();
                movieEntity.getCharacters().remove(characterEntity);
                movieRepository.save(movieEntity);
            }else{
                throw new ParamNotFound("character id not found");
            }
        }else{
            throw new ParamNotFound("movie id not found");
        }
    }
}
