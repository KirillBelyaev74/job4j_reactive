package ru.job4j.job4j_reactive.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_reactive.model.Weather;

import java.util.List;
import java.util.function.Function;

@Repository
public class WeatherRepository {

    private static class InstanceSessionFactory {
        private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    private SessionFactory getSessionFactory() {
        return InstanceSessionFactory.SESSION_FACTORY;
    }

    public <T> T action(Function<Session, T> action) {
        T t;
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            t = action.apply(session);
            session.getTransaction().commit();
        }
        return t;
    }

    public List<Weather> findAll() {
        return action(session -> session.createQuery("from Weather order by id").list());
    }

    public Weather findById(int id) {
        return action(session -> session.get(Weather.class, id));
    }

    public Weather findByMaxTemperature() {
        return action(session -> {
            Query<Weather> query = session.createQuery(
                    "from Weather where temperature = (select max(temperature) from Weather)");
            return query.uniqueResult();
        });
    }

    public List<Weather> findByAboveThisTemperature(int temperature) {
        return action(session -> {
            Query<Weather> query = session.createQuery(
                    "from Weather where temperature >: temperature");
            query.setParameter("temperature", temperature);
            return query.getResultList();
        });
    }
}
