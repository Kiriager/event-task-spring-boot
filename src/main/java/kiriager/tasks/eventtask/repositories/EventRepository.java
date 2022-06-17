package kiriager.tasks.eventtask.repositories;

import kiriager.tasks.eventtask.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
