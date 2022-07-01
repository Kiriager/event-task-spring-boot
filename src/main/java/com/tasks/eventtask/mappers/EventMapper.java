package com.tasks.eventtask.mappers;

import org.mapstruct.Mapper;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.dtos.EventDto;

@Mapper
public interface EventMapper {
    EventDto toDto(Event src);
    //Location fromDto(CreateLocationDto src);
    //Location fromDtoUpdate(CreateLocationDto src, Location entity);
}