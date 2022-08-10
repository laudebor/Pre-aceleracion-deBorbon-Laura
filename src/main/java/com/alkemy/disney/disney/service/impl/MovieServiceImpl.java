package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

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
    public void delete(Long id){
        movieRepository.deleteById(id);
    }
}
