package ru.forsh.voting_system_for_restaurants.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;
import ru.forsh.voting_system_for_restaurants.HasId;

import javax.persistence.*;

@MappedSuperclass
@Data
@RequiredArgsConstructor
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer>, HasId {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    public boolean isNew() {
        return getId() == null;
    }

}
