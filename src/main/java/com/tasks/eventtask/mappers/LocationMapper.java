package com.tasks.eventtask.mappers;

import org.mapstruct.Mapper;

import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.dtos.LocationDto;


@Mapper
public interface LocationMapper {
    LocationDto toDto(Location src);
}
