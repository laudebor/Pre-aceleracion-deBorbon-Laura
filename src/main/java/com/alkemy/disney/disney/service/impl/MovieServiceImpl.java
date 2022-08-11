package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.error.ServiceError;
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
    public MovieDTO save(MovieDTO dto) throws ServiceError {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO resultDTO = movieMapper.movieEntity2DTO(entitySaved, true);
        return resultDTO;
    }

    @Transactional
    public MovieDTO update(MovieDTO dto) throws ServiceError {
        if(dto.getId()==null){
            throw new ServiceError("id field empty");
        }else {
            MovieEntity entity = movieMapper.movieDTO2EntityUpdate(dto);
            MovieEntity entitySaved = movieRepository.save(entity);
            MovieDTO resultDTO = movieMapper.movieEntity2DTO(entitySaved, true);
            return resultDTO;
        }
    }

    public List<MovieDTO> getAll(){
        List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(movieRepository.findAll(), true);
        return movies;
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    public List<MovieBasicDTO> getByFilters(String name, Long genre, String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = movieMapper.movieEntityList2BasicDTO(entities);
        return dtos;
    }

    public void addCharacter(Long movieId, Long characterId) throws ServiceError {
        Optional<MovieEntity> movieResult = movieRepository.findById(movieId);
        if(movieResult.isPresent()){
            MovieEntity movieEntity = movieResult.get();
            Optional<CharacterEntity> characterResult = characterRepository.findById(characterId);
            if(characterResult.isPresent()){
                CharacterEntity characterEntity = characterResult.get();
                movieEntity.getCharacters().add(characterEntity);
                movieRepository.save(movieEntity);
            }else{
                throw new ServiceError("characterId not found");
            }
        }else{
            throw new ServiceError("movieId not found");
        }
    }

    public void removeCharacter(Long movieId, Long characterId) throws ServiceError {
        Optional<MovieEntity> movieResult = movieRepository.findById(movieId);
        if(movieResult.isPresent()){
            MovieEntity movieEntity = movieResult.get();
            Optional<CharacterEntity> characterResult = characterRepository.findById(characterId);
            if(characterResult.isPresent()){
                CharacterEntity characterEntity = characterResult.get();
                movieEntity.getCharacters().remove(characterEntity);
                movieRepository.save(movieEntity);
            }else{
                throw new ServiceError("characterId not found");
            }
        }else{
            throw new ServiceError("movieId not found");
        }
    }
}
