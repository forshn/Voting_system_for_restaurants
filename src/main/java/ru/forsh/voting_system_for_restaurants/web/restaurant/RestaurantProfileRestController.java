package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants/menus";

    @GetMapping
    public List<Restaurant> getAllWithMealsForToday() {
        return super.getAllWithMealsByDate(LocalDate.now());
    }
}