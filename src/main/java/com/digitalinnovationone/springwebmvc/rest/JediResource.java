package com.digitalinnovationone.springwebmvc.rest;

import com.digitalinnovationone.springwebmvc.model.Jedi;
import com.digitalinnovationone.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class JediResource {

    @Autowired
    private JediRepository repository;

    @GetMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Jedi> getAllJedi() {
        return repository.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) {

        final Optional<Jedi> jedi = Optional
                .ofNullable(repository
                        .findAll()
                        .get(id.intValue()));

        if (jedi.isPresent()) {
            return ResponseEntity.ok(jedi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/jedi")
    public Jedi createJedi(@Valid @RequestBody Jedi jedi) {
        return repository.add(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Object> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {

        final Optional<Jedi> jediEntity = Optional.ofNullable(repository.findAll().get(id.intValue()));
        final Jedi jedi;

        if (jediEntity.isPresent()) {
            jedi = jediEntity.get();
        } else {
            return ResponseEntity.notFound().build();
        }
        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return ResponseEntity.ok(repository.add(jedi));
    }

    @DeleteMapping("/api/jedi/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        final Optional<Jedi> jedi = Optional.ofNullable(repository.findAll().get(id.intValue()));

        if (jedi.isPresent()) {
            repository.findAll().remove(jedi.get());
            return ResponseEntity.noContent().build();
        } else {
           return ResponseEntity.notFound().build();
        }
    }
}
