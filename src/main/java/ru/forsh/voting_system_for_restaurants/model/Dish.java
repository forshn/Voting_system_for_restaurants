package ru.forsh.voting_system_for_restaurants.model;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 50_000)
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate added;

    public Dish() {
    }

    public Dish(Integer id, String name, int price, LocalDate added, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.restaurant = restaurant;
    }

    public Dish(String name, int price, Restaurant restaurant) {
        this(null, name, price, LocalDate.now(), restaurant);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dish dish = (Dish) o;
        return price == dish.price && restaurant.equals(dish.restaurant) && added.equals(dish.added);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, restaurant, added);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                ", added=" + added +
                '}';
    }
}