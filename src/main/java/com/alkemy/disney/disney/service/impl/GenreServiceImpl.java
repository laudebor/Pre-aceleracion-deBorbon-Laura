package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional
    public GenreDTO save(GenreDTO dto) throws ParamNotFound {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO resultDTO = genreMapper.genreEntity2DTO(entitySaved);
        return resultDTO;
    }

    public void delete(Long id) {
        Optional<GenreEntity> result = genreRepository.findById(id);
        if (result.isPresent()) {
            genreRepository.deleteById(id);
        } else {
            throw new ParamNotFound("genre id not found");
        }
    }

}
