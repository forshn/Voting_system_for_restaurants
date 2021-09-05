package ru.forsh.voting_system_for_restaurants.web.dish;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.forsh.voting_system_for_restaurants.DishTestData;
import ru.forsh.voting_system_for_restaurants.model.Dish;
import ru.forsh.voting_system_for_restaurants.util.exception.NotFoundException;
import ru.forsh.voting_system_for_restaurants.web.AbstractControllerTest;
import ru.forsh.voting_system_for_restaurants.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.forsh.voting_system_for_restaurants.DishTestData.*;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.RESTAURANT_1_ID;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.RESTAURANT_2_ID;
import static ru.forsh.voting_system_for_restaurants.TestUtil.readFromJson;
import static ru.forsh.voting_system_for_restaurants.TestUtil.userHttpBasic;
import static ru.forsh.voting_system_for_restaurants.UserTestData.NOT_FOUND;
import static ru.forsh.voting_system_for_restaurants.UserTestData.admin;

public class DishRestControllerTest extends AbstractControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(DishRestControllerTest.class);

    static final String REST_URL = "/rest/admin/restaurants/" + RESTAURANT_1_ID + "/dishes";

    @Autowired
    private DishAdminRestController controller;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + DISH_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + DISH_1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + DISH_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(DISH_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + DISH_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(controller.get(DISH_1_ID), DishTestData.getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
                .with(userHttpBasic(admin)));
        Dish created = readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(controller.get(newId), newDish);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/rest/admin/restaurants/" + RESTAURANT_2_ID + "/dishes")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish3, dish4, dish5));
    }
}