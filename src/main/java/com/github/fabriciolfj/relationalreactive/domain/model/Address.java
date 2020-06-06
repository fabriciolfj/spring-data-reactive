package com.github.fabriciolfj.relationalreactive.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("address")
public class Address {

    public Address(String description, Integer person) {
        this.description = description;
        this.person = person;
    }

    @Id
    private Integer id;
    @Column("description")
    private String description;
    @Column("person_id")
    private Integer person;
}
