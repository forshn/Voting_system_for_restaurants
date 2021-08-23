package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Restaurant r WHERE r.id=:id AND r.user.id=:userId")
//    int delete(@Param("id") int id, @Param("userId") int userId);
//
//    @Query("SELECT r FROM Restaurant r ORDER BY name DESC")
//    List<Restaurant> getAll();
//
//    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
//    List<Meal> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
//
//    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
//    Meal getWithUser(int id, int userId);
}