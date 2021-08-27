package ru.forsh.voting_system_for_restaurants.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.forsh.voting_system_for_restaurants.model.Dish;

import java.util.List;

@Repository
public class DishRepository {
    private final CrudDishRepository crudDishRepository;

    private final CrudMenuRepository crudMenuRepository;

    public DishRepository(CrudDishRepository crudDishRepository, CrudMenuRepository crudMenuRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setMenu(crudMenuRepository.getOne(menuId));
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }
    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }
}