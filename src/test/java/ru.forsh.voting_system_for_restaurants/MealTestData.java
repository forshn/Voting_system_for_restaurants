package ru.forsh.voting_system_for_restaurants;

import ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity;
import ru.forsh.voting_system_for_restaurants.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.restaurant1;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.restaurant2;

public class MealTestData {
    public static final TestMatcher<Meal> MEAL_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Meal.class, "restaurant", "added");

    public static final int MEAL_1_ID = AbstractBaseEntity.START_SEQ + 5;
    public static final int MEAL_2_ID = AbstractBaseEntity.START_SEQ + 6;
    public static final int MEAL_3_ID = AbstractBaseEntity.START_SEQ + 7;
    public static final int MEAL_4_ID = AbstractBaseEntity.START_SEQ + 8;
    public static final int MEAL_5_ID = AbstractBaseEntity.START_SEQ + 9;
    public static final Meal meal1 = new Meal(MEAL_1_ID, "Стейк", 1000,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Meal meal2 = new Meal(MEAL_2_ID, "Мимоза", 300,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Meal meal3 = new Meal(MEAL_3_ID, "Оливье", 400,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Meal meal4 = new Meal(MEAL_4_ID, "Яичница", 180,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Meal meal5 = new Meal(MEAL_5_ID, "Тост", 150,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Set<Meal> meals1 = Set.of(meal1, meal2);
    public static final Set<Meal> meals2 = Set.of(meal3, meal4, meal5);

    public static Meal getNew() {
        return new Meal(null, "NewRest", 999, LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setName("UpdatedName");
        updated.setPrice(666);
        return updated;
    }
}