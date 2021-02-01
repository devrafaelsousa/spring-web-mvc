package com.digitalinnovationone.springwebmvc.repository;

import com.digitalinnovationone.springwebmvc.model.Jedi;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JediRepository {

    private final List<Jedi> jedi;

    public JediRepository() {

        jedi = new ArrayList<>();
        jedi.add(new Jedi("Luke", "Skywalker"));
        jedi.add(new Jedi("Obi-Wan", "Kenobi"));
        jedi.add(new Jedi("Qui-Gon", "Jinn"));
    }

    public Object add(final Jedi jedi) {
        this.jedi.add(jedi);
        return jedi;
    }

    public List<Jedi> getAllJedi() {
        return this.jedi; }

    public List<Jedi> findAll() {

        return jedi;
    }

    public Optional<Jedi> findById(Long id) {
        return Optional.ofNullable(jedi.get(id.intValue()));
    }

    public void delete(Jedi jedi) {
        this.jedi.remove(jedi);
    }
}
