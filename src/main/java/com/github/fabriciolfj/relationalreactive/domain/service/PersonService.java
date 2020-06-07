package com.github.fabriciolfj.relationalreactive.domain.service;

import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonEventRepository;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final DatabaseClient databaseClient;
    private final PersonEventRepository personEventRepository;

    public Flux<Person> findAll() {
        return databaseClient.select().from(Person.class).fetch().all();
    }

    public Flux<Person> findAllByLastNAme(@PathVariable String lastName) {
        return this.personRepository.findAllByLastName(lastName);
    }

    public Flux<Person.PersonEvent> findAllEvents() {
        return personEventRepository.findAll();
    }

    public Mono<Void> create(final Person.PersonEvent event, final Person person) {
        return personRepository.save(person)
                .then(personEventRepository.save(event))
                .then();
    }
}
