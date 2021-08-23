package ru.forsh.voting_system_for_restaurants.repository;

import ru.forsh.voting_system_for_restaurants.model.Vote;

import java.util.List;

public class DataJpaVoteRepository implements VoteRepository {
    @Override
    public Vote save(Vote vote, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Vote get(int id, int userId) {
        return null;
    }

    @Override
    public List<Vote> getAll(int entityId) {
        return null;
    }
}
