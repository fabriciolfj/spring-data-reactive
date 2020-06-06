package com.github.fabriciolfj.relationalreactive.domain.repository;


import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonEventRepository extends ReactiveCrudRepository<Person.PersonEvent, Integer> {
}
