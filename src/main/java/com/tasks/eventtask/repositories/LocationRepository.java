package com.tasks.eventtask.repositories;

import org.springframework.data.repository.CrudRepository;
import com.tasks.eventtask.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
}

