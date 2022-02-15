package ru.forsh.voting_system_for_restaurants.to;

import lombok.Data;
import ru.forsh.voting_system_for_restaurants.HasId;

@Data
public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }
}