package ru.forsh.voting_system_for_restaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.forsh.voting_system_for_restaurants.model.Restaurant;
import ru.forsh.voting_system_for_restaurants.repository.RestaurantRepository;
import ru.forsh.voting_system_for_restaurants.web.SecurityUtil;

import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    static final String REST_URL = "/rest/admin/restaurant";
    //save restaurant

    //get restaurant

    //update restaurant

    //save menu to restaurant by id

    //update menu to restaurant by id

    //add restaurant
}