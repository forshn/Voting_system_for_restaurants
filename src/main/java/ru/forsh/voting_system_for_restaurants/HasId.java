package ru.forsh.voting_system_for_restaurants;

import org.springframework.util.Assert;

public interface HasId {
    Integer getId();

    void setId(Integer id);q

    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}