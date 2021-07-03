package ru.job4j.job4j_reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.job4j_reactive.model.Weather;
import ru.job4j.job4j_reactive.repository.WeatherRepository;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    @Autowired
    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    public Flux<Weather> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    public Mono<Weather> findById(int id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Mono<Weather> findByMaxTemperature() {
        return Mono.justOrEmpty(repository.findByMaxTemperature());
    }

    public Flux<Weather> findByAboveThisTemperature(int temperature) {
        return Flux.fromIterable(repository.findByAboveThisTemperature(temperature));
    }
}
