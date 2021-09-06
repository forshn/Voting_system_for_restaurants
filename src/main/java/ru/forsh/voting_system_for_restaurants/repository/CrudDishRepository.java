package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Meal, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Meal d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Meal d WHERE d.restaurant.id=:restaurantId")
    List<Meal> getAllByRestaurantId(@Param("restaurantId") int restaurantId);
}