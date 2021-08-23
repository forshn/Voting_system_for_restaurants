package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.forsh.voting_system_for_restaurants.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
}
