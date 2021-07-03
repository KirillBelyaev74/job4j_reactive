package ru.job4j.job4j_reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.job4j_reactive.service.WeatherService;
import ru.job4j.job4j_reactive.model.Weather;

import java.time.Duration;

@RestController
public class WeatherController {

    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAll() {
        Flux<Weather> data = service.findAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/hotmax")
    public Mono<Weather> findMaxHot() {
        return service.findByMaxTemperature();
    }

    @GetMapping("/above/{temperature}")
    public Flux<Weather> findByAboveThisTemperature(@PathVariable int temperature) {
        return service.findByAboveThisTemperature(temperature);
    }
}
