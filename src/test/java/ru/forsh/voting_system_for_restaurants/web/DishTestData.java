package java.ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.Dish;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.util.Set;

import static java.ru.forsh.voting_system_for_restaurants.web.MenuTestData.menuRest1;
import static java.ru.forsh.voting_system_for_restaurants.web.MenuTestData.menuRest2;
import static java.ru.forsh.voting_system_for_restaurants.web.RestaurantTestData.*;

public class DishTestData {
    public static final TestMatcher<Restaurant> DISH_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final Dish dish1 = new Dish(DISH_1_ID, "Греча с курицей", 1200, menuRest1);
    public static final Dish dish2 = new Dish(DISH_2_ID, "Севиче с рисом", 2300, menuRest1);
    public static final Dish dish3 = new Dish(DISH_3_ID, "Утка с яблоками", 1400, menuRest2);
    public static final Dish dish4 = new Dish(DISH_4_ID, "Яичница", 280, menuRest2);
    public static final Dish dish5 = new Dish(DISH_5_ID, "Тост", 150, menuRest2);

    public static final Set<Dish> dishes1 = Set.of(dish1, dish2);
    public static final Set<Dish> dishes2 = Set.of(dish3, dish4, dish5);
}