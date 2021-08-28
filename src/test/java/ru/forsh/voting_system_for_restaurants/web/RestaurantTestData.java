package java.ru.forsh.voting_system_for_restaurants.web;

import ru.forsh.voting_system_for_restaurants.model.Restaurant;

import static java.ru.forsh.voting_system_for_restaurants.web.DishTestData.dishes1;
import static java.ru.forsh.voting_system_for_restaurants.web.DishTestData.dishes2;
import static ru.forsh.voting_system_for_restaurants.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "dishes", "votes");

    public static final int RESTAURANT_1_ID = START_SEQ + 2;
    public static final int RESTAURANT_2_ID = START_SEQ + 3;
    public static final int RESTAURANT_3_ID = START_SEQ + 4;
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Кафе", dishes1);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "Бар", dishes2);
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_3_ID, "Ресторан", null);

    public static Restaurant getNew() {
        return new Restaurant(null, "NewRest", dishes2);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("UpdatedName");
        updated.setDishes(dishes2);
        return updated;
    }
}
