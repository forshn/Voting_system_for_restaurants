package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.forsh.voting_system_for_restaurants.model.User;

import java.util.List;

@Repository
public class UserRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    public UserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}