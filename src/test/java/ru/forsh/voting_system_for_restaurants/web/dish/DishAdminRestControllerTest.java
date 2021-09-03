package ru.forsh.voting_system_for_restaurants.web.dish;


@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestControllerTest {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    public DishAdminRestController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Cacheable("dishes")
    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("getAll dishes for restaurantId={}", restaurantId);
        return dishRepository.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id={}", id);
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create {}", dish);
        checkNew(dish);
        Dish created = dishRepository.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/" + restaurantId + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "dishes", allEntries = true)
    public void update(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("update dish {} with id={} to restaurant with id={}", dish, id, restaurantId);
        assureIdConsistent(dish, id);
        checkNotFoundWithId(dishRepository.save(dish, restaurantId), dish.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id={}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }
}
