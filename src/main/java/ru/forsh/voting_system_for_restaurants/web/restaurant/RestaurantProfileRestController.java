package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.model.Vote;
import ru.forsh.voting_system_for_restaurants.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

//@RestController
//@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class RestaurantProfileRestController extends AbstractRestaurantController {
//    static final String REST_URL = "/rest/profile/restaurant";

    @Autowired
    private VoteRepository voteRepository;

    public List<Restaurant> getAllWithDishesForToday() {
        return super.getAllWithDishesByDate(LocalDate.now());
    }

    public Vote vote(int restaurantId) {
        // get userId?
        // check ValidationUtil.canVote
        // save vote by restId, userId, LocalDate.now()
        return null;
    }
}