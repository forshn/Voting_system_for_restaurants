package ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}