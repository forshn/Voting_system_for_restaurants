package java.ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import static java.ru.forsh.voting_system_for_restaurants.web.MenuTestData.menuListRest1;
import static java.ru.forsh.voting_system_for_restaurants.web.MenuTestData.menuListRest2;
import static ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "menus", "votes");

    public static final int RESTAURANT_1_ID = START_SEQ + 2;
    public static final int RESTAURANT_2_ID = START_SEQ + 3;
    public static final int RESTAURANT_3_ID = START_SEQ + 4;
    public static final int MENU_1_ID = START_SEQ + 5;
    public static final int MENU_2_ID = START_SEQ + 6;
    public static final int DISH_1_ID = START_SEQ + 7;
    public static final int DISH_2_ID = START_SEQ + 8;
    public static final int DISH_3_ID = START_SEQ + 9;
    public static final int DISH_4_ID = START_SEQ + 10;
    public static final int DISH_5_ID = START_SEQ + 11;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Кафе", menuListRest1);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "Бар", menuListRest2);
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_3_ID, "Ресторан", null);

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан", menuListRest1);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("Новое название");
        updated.setMenus(menuListRest2);
        return updated;
    }
}
