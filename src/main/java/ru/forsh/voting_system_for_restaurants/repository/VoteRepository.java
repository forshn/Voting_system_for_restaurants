package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote get(int id);

    Vote save(Vote vote, int restaurant_id, int userId);

    boolean delete(int id);

    List<Vote> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);

    List<Vote> getAllByUserAndDate(int user_id, LocalDate date);

    List<Vote> getAllByRestaurantAndUser(int restaurant_id, int user_id);
}