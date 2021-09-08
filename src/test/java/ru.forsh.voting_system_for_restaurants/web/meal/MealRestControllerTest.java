package ru.forsh.voting_system_for_restaurants.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.forsh.voting_system_for_restaurants.MealTestData;
import ru.forsh.voting_system_for_restaurants.model.Meal;
import ru.forsh.voting_system_for_restaurants.util.exception.NotFoundException;
import ru.forsh.voting_system_for_restaurants.web.AbstractControllerTest;
import ru.forsh.voting_system_for_restaurants.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.forsh.voting_system_for_restaurants.MealTestData.*;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.RESTAURANT_1_ID;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.RESTAURANT_2_ID;
import static ru.forsh.voting_system_for_restaurants.TestUtil.readFromJson;
import static ru.forsh.voting_system_for_restaurants.TestUtil.userHttpBasic;
import static ru.forsh.voting_system_for_restaurants.UserTestData.NOT_FOUND;
import static ru.forsh.voting_system_for_restaurants.UserTestData.admin;

public class MealRestControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/rest/admin/restaurants/" + RESTAURANT_1_ID + "/meals";

    @Autowired
    private MealAdminRestController controller;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MEAL_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MEAL_1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MEAL_1_ID))
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
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + MEAL_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(MEAL_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + MEAL_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        MEAL_MATCHER.assertMatch(controller.get(MEAL_1_ID), MealTestData.getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Meal newMeal = MealTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal))
                .with(userHttpBasic(admin)));
        Meal created = readFromJson(action, Meal.class);
        int newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(controller.get(newId), newMeal);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/rest/admin/restaurants/" + RESTAURANT_2_ID + "/meals")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MEAL_3, MEAL_4, MEAL_5));
    }
}