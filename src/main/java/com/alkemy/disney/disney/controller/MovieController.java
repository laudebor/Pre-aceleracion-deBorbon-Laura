package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto){
        try{
            MovieDTO movieSaved = movieService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
        } catch (ServiceError e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/update")
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO dto){
        try{
            MovieDTO movieUpdated = movieService.update(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(movieUpdated);
        } catch (ServiceError e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
