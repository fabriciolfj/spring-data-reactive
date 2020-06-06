package com.github.fabriciolfj.relationalreactive.domain.repository;

import com.github.fabriciolfj.relationalreactive.domain.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AddressRepository extends ReactiveCrudRepository<Address, Integer> {
}
