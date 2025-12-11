package com.person.person.controllers;

import com.person.person.dto.PersonDTO;
import com.person.person.dto.PersonDepDTO;
import com.person.person.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping(value = "/person")
public class PersonControler {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDepDTO> findByid(@PathVariable Long id){
        PersonDepDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    //@PostMapping
    public ResponseEntity<PersonDepDTO> insert(@RequestBody PersonDepDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
