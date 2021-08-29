package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.model.Vote;
import ru.forsh.voting_system_for_restaurants.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    public List<Restaurant> getAllWithDishesForToday() {
        return super.getAllWithDishesByDate(LocalDate.now());
    }

    public Vote vote(int restaurantId) {
        return null;
    }
}