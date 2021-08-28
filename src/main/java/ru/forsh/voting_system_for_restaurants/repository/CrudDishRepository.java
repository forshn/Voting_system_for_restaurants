package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant_id")
    List<Dish> getAllByRestaurant(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant_id AND d.added=:date")
    List<Dish> getAllByRestaurantAndDate(@Param("restaurant_id") int restaurantId, @Param("date") LocalDate date);
}