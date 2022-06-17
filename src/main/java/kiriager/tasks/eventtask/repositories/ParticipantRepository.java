package kiriager.tasks.eventtask.repositories;

import kiriager.tasks.eventtask.domain.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}
