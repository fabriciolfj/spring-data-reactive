package com.github.fabriciolfj.relationalreactive.domain.repository;

import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {

    @Query("SELECT * FROM person WHERE last_name = :lastName")
    Flux<Person> findAllByLastName(final String lastName);
}
