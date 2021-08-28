package ru.forsh.voting_system_for_restaurants.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this(null, name, null);
    }

    public Restaurant(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.name = name;
        this.dishes = dishes;
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getDishes());
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}