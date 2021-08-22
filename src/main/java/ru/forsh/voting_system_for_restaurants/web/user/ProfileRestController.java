package ru.forsh.voting_system_for_restaurants.web.user;

import org.springframework.stereotype.Controller;
import ru.forsh.voting_system_for_restaurants.model.User;

import static ru.forsh.voting_system_for_restaurants.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}