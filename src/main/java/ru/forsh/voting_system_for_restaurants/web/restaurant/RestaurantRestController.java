package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.repository.RestaurantRepository;
import ru.forsh.voting_system_for_restaurants.web.SecurityUtil;

import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

@Controller
public class RestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    public RestaurantRestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        return repository.save(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, id);
        log.info("update {} for user {}", restaurant, userId);
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return repository.getAll(userId);
    }

    public List<Restaurant> getAllWithMenu() {
        int userId = SecurityUtil.authUserId();
        log.info("getAllWithMenu for user {}", userId);
        return repository.getAllWithMenu();
    }
}