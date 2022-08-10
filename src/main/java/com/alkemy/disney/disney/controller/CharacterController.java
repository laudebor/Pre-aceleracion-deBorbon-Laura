package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.error.ServiceError;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO dto){
        try {
            CharacterDTO characterSaved = characterService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
        }catch(ServiceError e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO dto){
        try{
            CharacterDTO characterUpdated = characterService.update(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterUpdated);
        } catch (ServiceError e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAll(){
        List<CharacterDTO> characters = characterService.getAll();
        return ResponseEntity.ok().body(characters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
