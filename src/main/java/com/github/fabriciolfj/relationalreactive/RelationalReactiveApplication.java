package com.github.fabriciolfj.relationalreactive;

import com.github.fabriciolfj.relationalreactive.domain.model.Address;
import com.github.fabriciolfj.relationalreactive.domain.model.Person;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonEventRepository;
import com.github.fabriciolfj.relationalreactive.domain.repository.PersonRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;
import java.util.Arrays;

@EnableR2dbcRepositories
@SpringBootApplication
@Slf4j
public class RelationalReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalReactiveApplication.class, args);
	}

	@Bean
	public CommandLineRunner demonstracao(PersonRepository repository, PersonEventRepository eventRepository) {
		return (args) -> {
			repository.saveAll(Arrays.asList(new Person("Fabricio", "Jacob"),
					new Person("Suzana", "Jacob"),
					new Person("Roberto", "Silva"),
					new Person("Carlos", "Silva"),
					new Person("Michele", "Aparecida")))
					.blockLast(Duration.ofSeconds(10));
		};
	}

	/*
	* Forma simplificada de executar os scritps de criação.
	* */
	/*@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}*/
}
