package ru.forsh.voting_system_for_restaurants.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.forsh.voting_system_for_restaurants.View;
import ru.forsh.voting_system_for_restaurants.model.Meal;
import ru.forsh.voting_system_for_restaurants.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    public DishAdminRestController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Cacheable("dishes")
    @GetMapping
    public List<Meal> getAll(@PathVariable int restaurantId) {
        log.info("getAll dishes for restaurantId={}", restaurantId);
        return dishRepository.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        log.info("get dish with id={}", id);
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int restaurantId) {
        log.info("create {}", meal);
        checkNew(meal);
        Meal created = dishRepository.save(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/" + restaurantId + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "dishes", allEntries = true)
    public void update(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("update dish {} with id={} to restaurant with id={}", meal, id, restaurantId);
        assureIdConsistent(meal, id);
        checkNotFoundWithId(dishRepository.save(meal, restaurantId), meal.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id={}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }
}