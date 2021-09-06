package ru.forsh.voting_system_for_restaurants.model;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate added;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<Meal> meals;

    public Menu() {
    }

    public Menu(@NotNull Restaurant restaurant, @NotNull LocalDate added, Set<Meal> meals) {
        this(null, restaurant, added, meals);
    }

    public Menu(Integer id, @NotNull Restaurant restaurant, @NotNull LocalDate added, Set<Meal> meals) {
        super(id);
        this.restaurant = restaurant;
        this.added = added;
        this.meals = meals;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public Set<Meal> getDishes() {
        return meals;
    }

    public void setDishes(Set<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return restaurant.equals(menu.restaurant) && added.equals(menu.added) && Objects.equals(meals, menu.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restaurant, added, meals);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", added=" + added +
                ", meals=" + meals +
                '}';
    }
}