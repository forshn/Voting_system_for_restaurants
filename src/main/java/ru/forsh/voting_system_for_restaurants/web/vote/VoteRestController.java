package ru.forsh.voting_system_for_restaurants.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.forsh.voting_system_for_restaurants.model.Vote;
import ru.forsh.voting_system_for_restaurants.repository.VoteRepository;
import ru.forsh.voting_system_for_restaurants.util.DateTimeUtil;
import ru.forsh.voting_system_for_restaurants.util.exception.ApplicationException;
import ru.forsh.voting_system_for_restaurants.util.exception.ErrorType;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.checkNotFound;
import static ru.forsh.voting_system_for_restaurants.util.ValidationUtil.checkNotFoundWithId;
import static ru.forsh.voting_system_for_restaurants.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository repository;

    public VoteRestController(VoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/votes")
    public List<Vote> getAllByUser() {
        log.info("getAll votes for userId={}", authUserId());
        return repository.getAllByUser(authUserId());
    }

    @GetMapping("/{restaurantId}/votes")
    public List<Vote> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("getAll votes for restaurantId={}", restaurantId);
        return repository.getAllByRestaurant(restaurantId);
    }

    @GetMapping("/votes/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote with id={}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @PostMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@PathVariable int restaurantId) {
        log.info("create new vote for restaurantId = {}", restaurantId);
        Vote created = repository.save(new Vote(), authUserId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/votes/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int restaurantId) {
        log.info("update vote to restaurant with id={}", restaurantId);
        Vote vote = checkNotFound(repository.getByUserAndDate(authUserId(), LocalDate.now()), "Vote not found!");
        if (DateTimeUtil.canReVote(LocalTime.now())) {
            repository.save(vote, authUserId(), restaurantId);
        } else {
            throw new ApplicationException("Run out of time", ErrorType.VALIDATION_ERROR);
        }
    }

    @DeleteMapping("/votes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote with id={}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }
}