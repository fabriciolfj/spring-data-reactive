package com.github.fabriciolfj.relationalreactive.api.controller;

import com.github.fabriciolfj.relationalreactive.api.dto.AddressDTO;
import com.github.fabriciolfj.relationalreactive.api.mapper.AddressMapper;
import com.github.fabriciolfj.relationalreactive.domain.model.Address;
import com.github.fabriciolfj.relationalreactive.domain.repository.AddressRepository;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonRepository;
import com.github.fabriciolfj.relationalreactive.domain.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final PersonRepository personRepository;
    private final AddressMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Address> create(@RequestBody AddressDTO dto) {
        var address = mapper.toDomain(dto);
        return personRepository.findById(dto.getIdPerson())
                .flatMap(p -> {
                    address.setPerson(p.getId());
                    return addressService.save(address);
                });
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Address> findAll() {
        return addressService.findAll();
    }
}
