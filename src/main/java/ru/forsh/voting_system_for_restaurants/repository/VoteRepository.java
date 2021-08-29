package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepository {
    private final CrudVoteRepository crudVoteRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    private final CrudUserRepository crudUserRepository;

    public VoteRepository(CrudVoteRepository crudVoteRepository, CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId()) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
        return crudVoteRepository.getByUserAndDate(userId, date);
    }

    public List<Vote> getAllByUser(int userId) {
        return crudVoteRepository.getByUserId(userId);
    }

    public List<Vote> getAllByRestaurant(int restaurantId) {
        return crudVoteRepository.getByRestaurantId(restaurantId);
    }
}