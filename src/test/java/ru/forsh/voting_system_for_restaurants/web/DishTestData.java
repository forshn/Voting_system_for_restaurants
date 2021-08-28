package java.ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.Dish;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static java.ru.forsh.voting_system_for_restaurants.web.RestaurantTestData.restaurant1;
import static java.ru.forsh.voting_system_for_restaurants.web.RestaurantTestData.restaurant2;
import static ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final TestMatcher<Restaurant> DISH_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int DISH_1_ID = START_SEQ + 5;
    public static final int DISH_2_ID = START_SEQ + 6;
    public static final int DISH_3_ID = START_SEQ + 7;
    public static final int DISH_4_ID = START_SEQ + 8;
    public static final int DISH_5_ID = START_SEQ + 9;
    public static final Dish dish1 = new Dish(DISH_1_ID, "Утка по пекински", 2000,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Dish dish2 = new Dish(DISH_2_ID, "Гренки", 300,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Dish dish3 = new Dish(DISH_3_ID, "Салат цезарь", 400,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Dish dish4 = new Dish(DISH_4_ID, "Омлет", 180,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Dish dish5 = new Dish(DISH_5_ID, "Печенье", 150,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Set<Dish> dishes1 = Set.of(dish1, dish2);
    public static final Set<Dish> dishes2 = Set.of(dish3, dish4, dish5);
}