package ru.forsh.voting_system_for_restaurants.model;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "voted", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate voted;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate voted) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.voted = voted;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVoted() {
        return voted;
    }

    public void setVoted(LocalDate voted) {
        this.voted = voted;
    }
}