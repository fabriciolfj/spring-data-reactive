package com.github.fabriciolfj.relationalreactive.domain.service;

import com.github.fabriciolfj.relationalreactive.domain.model.Address;
import com.github.fabriciolfj.relationalreactive.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public Flux<Address> findAll() {
        return addressRepository.findAll();
    }

    public Mono<Address> save(Address address) {
        return addressRepository.save(address);
    }

    public Mono<Address> findById(final Integer id) {
        return addressRepository.findById(id).onErrorResume(e -> Mono.error(() -> new RuntimeException(e.getMessage())));
    }
}
