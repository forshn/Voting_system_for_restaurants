package ru.forsh.voting_system_for_restaurants.repository;

import ru.forsh.voting_system_for_restaurants.model.Vote;

import java.util.List;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Vote get(int id, int userId);

    List<Vote> getAll(int entityId);
}