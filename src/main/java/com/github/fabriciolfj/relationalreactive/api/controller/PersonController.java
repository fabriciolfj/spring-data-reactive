package com.github.fabriciolfj.relationalreactive.api.controller;

import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonEventRepository;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonRepository;
import com.github.fabriciolfj.relationalreactive.domain.service.PersonService;
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

    private final PersonService personService;

    @GetMapping
    public Flux<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/by-name/{lastName}")
    public Flux<Person> findAllByLastNAme(@PathVariable String lastName) {
        return personService.findAllByLastNAme(lastName);
    }

    @PostMapping("/create/{firstName}/{lastName}")
    public Mono<Void> create(@PathVariable String firstName, @PathVariable String lastName) {
        var person = new Person(firstName, lastName);
        var event = new Person.PersonEvent(firstName, lastName, "CREATED");

        return personService.create(event, person);
    }

    @GetMapping("/events")
    public Flux<Person.PersonEvent> findAllEvents() {
        return personService.findAllEvents();
    }
}
