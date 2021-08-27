package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant getWithMenu(int id) {
        log.info("getWithMenu with id={}", id);
        return repository.getWithMenu(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public List<Restaurant> getAllWithMenu() {
        log.info("getAllWithMenu");
        return repository.getAllWithMenu();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate date) {
        log.info("getAllWithMenuByDate={}", date);
        return repository.getAllWithMenuByDate(date);
    }
}