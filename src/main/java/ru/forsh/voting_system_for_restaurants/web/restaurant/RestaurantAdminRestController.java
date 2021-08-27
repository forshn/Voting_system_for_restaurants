package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.repository.RestaurantRepository;
import ru.forsh.voting_system_for_restaurants.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

@Controller
public class RestaurantAdminRestController extends AbstractRestaurantController {
//    static final String REST_URL = "/rest/admin/restaurant";

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public Restaurant getWithMenu(int id) {
        return super.getWithMenu(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        super.update(restaurant, id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public List<Restaurant> getAllWithMenu() {
        return super.getAllWithMenu();
    }

    @Override
    public List<Restaurant> getAllWithMenuByDate(LocalDate date) {
        return super.getAllWithMenuByDate(date);
    }
}