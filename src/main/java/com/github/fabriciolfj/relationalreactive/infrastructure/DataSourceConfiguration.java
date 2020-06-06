package com.github.fabriciolfj.relationalreactive.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.BaseStream;


/*
* Uma forma de executar os scripts de criação das tabelas.
* */
//@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Bean
    public ApplicationRunner seeder(DatabaseClient client) {
        return args -> getSchema()
                .flatMap(sql -> executeSql(client, sql))
                .subscribe(count -> log.info("Schema created"));
    }

    private Mono<String> getSchema() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("schema.sql").toURI());
        return Flux
                .using(() -> Files.lines(path), Flux::fromStream, BaseStream::close)
                .reduce((line1, line2) -> line1 + "\n" + line2);
    }

    private Mono<Integer> executeSql(DatabaseClient client, String sql) {
        return client.execute(sql).fetch().rowsUpdated();
    }
}
