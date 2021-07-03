package ru.job4j.job4j_reactive.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "city", nullable = false, unique = true)
    private String city;

    @Column(name = "temperature", nullable = false, unique = true)
    private int temperature;

    public Weather() {
    }

    public Weather(String city, int temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weather weather = (Weather) o;
        return id == weather.id && temperature == weather.temperature
                && Objects.equals(city, weather.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, temperature);
    }

    @Override
    public String toString() {
        return "Weather { "
                + "id = " + id
                + ", city = '" + city + '\''
                + ", temperature = " + temperature
                + '}';
    }
}
