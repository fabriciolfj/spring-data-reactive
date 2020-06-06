package com.github.fabriciolfj.relationalreactive.api.mapper;

import com.github.fabriciolfj.relationalreactive.api.dto.AddressDTO;
import com.github.fabriciolfj.relationalreactive.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "person", ignore = true)
    Address toDomain(final AddressDTO dto);
}
