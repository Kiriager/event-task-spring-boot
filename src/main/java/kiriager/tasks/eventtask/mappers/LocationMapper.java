package kiriager.tasks.eventtask.mappers;

import org.mapstruct.Mapper;

import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.dto.location.LocationDto;

@Mapper
public interface LocationMapper {
    LocationDto toDto(Location src);
}
