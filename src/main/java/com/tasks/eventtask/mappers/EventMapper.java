package com.tasks.eventtask.mappers;

import java.util.Set;

import org.mapstruct.Mapper;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.dtos.CreateEventDto;
import com.tasks.eventtask.dtos.EventDto;

@Mapper
public interface EventMapper {
    EventDto toDto(Event src);
    Set<EventDto> toDtos(Iterable<Event> src);
    Event fromDto(CreateEventDto src);
    Event fromDtoUpdate(CreateEventDto src, Event entity);
}