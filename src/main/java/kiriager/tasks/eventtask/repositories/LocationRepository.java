package kiriager.tasks.eventtask.repositories;

import kiriager.tasks.eventtask.domain.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
