package little.old.me.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.domain.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    // No need for custom methods, Panache provides methods like:
    // find(), findById(), persist(), delete(), etc.
}
