package com.digitalinnovationone.springwebmvc.service;

import com.digitalinnovationone.springwebmvc.exception.JediNotFoundException;
import com.digitalinnovationone.springwebmvc.model.Jedi;
import com.digitalinnovationone.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {

        return repository.findAll();
    }

    public Jedi findById(Long id) {

        final Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Object save(Jedi jedi) {

        return repository.add(jedi);
    }

    public Object update(Long id, Jedi dto) {

        final Optional<Jedi> jediEntity = repository.findById(id);
        final Jedi jedi;

        if (jediEntity.isPresent()) {
            jedi = jediEntity.get();
        } else {
            throw new JediNotFoundException();
        }

        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return repository.add(jedi);
    }

    public void delete(@PathVariable("id") Long id) {

        final Jedi jedi = findById(id);

        repository.delete(jedi);
    }
}
