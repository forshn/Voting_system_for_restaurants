package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.repository.MealRepository;
import ru.forsh.voting_system_for_restaurants.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    @Autowired
    protected MealRepository mealRepository;

    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public Restaurant getWithMeals(int id, @Nullable LocalDate date) {
        if (date == null) {
            log.info("getWithMeals with id={}", id);
            return repository.getWithMeals(id);
        }
        log.info("getWithMealsByDate={} with id={}", date, id);
        return repository.getWithMealsByDate(id, date);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllWithMealsByDate(LocalDate date) {
        log.info("getAllWithMealsByDate={}", date);
        return repository.getAllWithMealsByDate(date);
    }
}