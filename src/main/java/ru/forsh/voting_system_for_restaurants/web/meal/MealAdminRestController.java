package ru.forsh.voting_system_for_restaurants.web.meal;

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
import ru.forsh.voting_system_for_restaurants.repository.MealRepository;

import java.net.URI;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/meals";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealRepository mealRepository;

    public MealAdminRestController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Cacheable("meals")
    @GetMapping
    public List<Meal> getAll(@PathVariable int restaurantId) {
        log.info("getAll meals for restaurantId={}", restaurantId);
        return mealRepository.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        log.info("get meal with id={}", id);
        return checkNotFoundWithId(mealRepository.get(id), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int restaurantId) {
        log.info("create {}", meal);
        checkNew(meal);
        Meal created = mealRepository.save(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/" + restaurantId + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "meals", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("update meal {} with id={} to restaurant with id={}", meal, id, restaurantId);
        assureIdConsistent(meal, id);
        checkNotFoundWithId(mealRepository.save(meal, restaurantId), meal.id());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "meals", allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete meal with id={}", id);
        checkNotFoundWithId(mealRepository.delete(id), id);
    }
}