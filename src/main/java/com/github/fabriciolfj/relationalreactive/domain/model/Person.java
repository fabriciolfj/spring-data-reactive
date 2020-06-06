package com.github.fabriciolfj.relationalreactive.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("person")
public class Person {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Data
    @Table("person_event")
    public static class PersonEvent implements Persistable<Integer> {

        @Id
        Integer id;

        final String firstName;
        final String lastName;
        final String event;

        @PersistenceConstructor
        public PersonEvent(Integer id, String firstName, String lastName, String event) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.event = event;
            this.id = id;
        }

        public PersonEvent(String firstName, String lastName, String event) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.event = event;
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public boolean isNew() {
            return true;
        }
    }
}
