package com.github.fabriciolfj.relationalreactive.api.controller;

import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonEventRepository;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/persons")
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonEventRepository personEventRepository;
    private final DatabaseClient databaseClient;

    @GetMapping
    public Flux<Person> findAll() {
        return databaseClient.select().from(Person.class).fetch().all();
    }

    @GetMapping("/by-name/{lastName}")
    public Flux<Person> findAllByLastNAme(@PathVariable String lastName) {
        return this.personRepository.findAllByLastName(lastName);
    }

    @PostMapping("/create/{firstName}/{lastName}")
    public Mono<Void> create(@PathVariable String firstName, @PathVariable String lastName) {
        var person = new Person(firstName, lastName);
        var event = new Person.PersonEvent(1, firstName, lastName, "CREATED");

        return personRepository.save(person)
                .then(personEventRepository.save(event))
                .then();
    }

    @GetMapping("/events")
    public Flux<Person.PersonEvent> findAllEvents() {
        return personEventRepository.findAll();
    }
}
