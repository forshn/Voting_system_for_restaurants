package ru.forsh.voting_system_for_restaurants;

import ru.forsh.voting_system_for_restaurants.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.restaurant1;
import static ru.forsh.voting_system_for_restaurants.RestaurantTestData.restaurant2;
import static ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final TestMatcher<Meal> DISH_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Meal.class, "restaurant", "added");

    public static final int DISH_1_ID = START_SEQ + 5;
    public static final int DISH_2_ID = START_SEQ + 6;
    public static final int DISH_3_ID = START_SEQ + 7;
    public static final int DISH_4_ID = START_SEQ + 8;
    public static final int DISH_5_ID = START_SEQ + 9;
    public static final Meal MEAL_1 = new Meal(DISH_1_ID, "Стейк", 1000,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Meal MEAL_2 = new Meal(DISH_2_ID, "Мимоза", 300,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Meal MEAL_3 = new Meal(DISH_3_ID, "Оливье", 400,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Meal MEAL_4 = new Meal(DISH_4_ID, "Яичница", 180,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Meal MEAL_5 = new Meal(DISH_5_ID, "Тост", 150,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Set<Meal> DISHES_1 = Set.of(MEAL_1, MEAL_2);
    public static final Set<Meal> DISHES_2 = Set.of(MEAL_3, MEAL_4, MEAL_5);

    public static Meal getNew() {
        return new Meal(null, "NewRest", 999, LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_1);
        updated.setName("UpdatedName");
        updated.setPrice(1111);
        return updated;
    }
}