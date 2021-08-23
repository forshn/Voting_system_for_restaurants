package ru.forsh.voting_system_for_restaurants.repository;

import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.util.List;

public class DataJpaRestaurantRepository implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return null;
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return null;
    }

    @Override
    public List<Restaurant> getAllWithMenu() {
        return null;
    }
}
