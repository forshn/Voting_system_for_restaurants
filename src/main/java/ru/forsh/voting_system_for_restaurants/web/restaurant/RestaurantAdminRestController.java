package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.springframework.stereotype.Controller;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RestaurantAdminRestController extends AbstractRestaurantController {
//    static final String REST_URL = "/rest/admin/restaurant";

    @Override
    public Restaurant get(int id) {
        return super.get(id);
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
    public Restaurant getWithDishes(int id) {
        return super.getWithDishes(id);
    }

    @Override
    public List<Restaurant> getWithDishesByDate(int id, LocalDate date) {
        return super.getWithDishesByDate(id, date);
    }

    @Override
    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        return super.getAllWithDishesByDate(date);
    }
}