package java.ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.Menu;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.ru.forsh.voting_system_for_restaurants.web.DishTestData.dishes1;
import static java.ru.forsh.voting_system_for_restaurants.web.RestaurantTestData.*;

public class MenuTestData {
    public static final TestMatcher<Restaurant> MENU_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final Menu menuRest1 = new Menu(MENU_1_ID, restaurant1, LocalDate.of(2021, Month.MARCH, 1), dishes1);
    public static final Menu menuRest2 = new Menu(MENU_2_ID, restaurant2, LocalDate.of(2021, Month.MARCH, 1),
            DishTestData.dishes2);
    public static final List<Menu> menuListRest1 = Arrays.asList(menuRest1);
    public static final List<Menu> menuListRest2 = Arrays.asList(menuRest2);
}
