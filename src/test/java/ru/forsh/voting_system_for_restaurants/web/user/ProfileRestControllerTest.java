package ru.forsh.voting_system_for_restaurants.web.user;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.util.exception.NotFoundException;
import ru.forsh.voting_system_for_restaurants.web.AbstractControllerTest;
import ru.forsh.voting_system_for_restaurants.web.restaurant.RestaurantAdminRestController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantAdminRestControllerTest extends AbstractControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(RestaurantAdminRestControllerTest.class);

    @Autowired
    private RestaurantAdminRestController controller;

    @Test
    public void get() {
        Restaurant restaurant = controller.get(RESTAURANT_1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.restaurant1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        controller.update(updated, updated.id());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_1_ID), RestaurantTestData.getUpdated());
    }

    @Test
    public void getAll() {
        List<Restaurant> all = controller.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant1, restaurant2, restaurant3);
    }

    @Test
    public void create() {
        Restaurant created = controller.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void delete() {
        controller.delete(RESTAURANT_1_ID);
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_1_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}