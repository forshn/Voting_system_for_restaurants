package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Meal;

import java.util.List;

@Repository
public class MealRepository {
    private final CrudMealRepository crudMealRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MealRepository(CrudMealRepository crudMealRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    public boolean delete(int id) {
        return crudMealRepository.delete(id) != 0;
    }

    public Meal get(int id) {
        return crudMealRepository.findById(id).orElse(null);
    }

    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAllByRestaurantId(restaurantId);
    }
}