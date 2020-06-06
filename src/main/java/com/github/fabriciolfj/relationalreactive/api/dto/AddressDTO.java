package com.github.fabriciolfj.relationalreactive.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String description;
    @JsonProperty("person_id")
    private Integer idPerson;
}
