package com.tasks.eventtask.mappers;

import java.util.HashSet;
import java.util.Set;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.dtos.CreateEventDto;
import com.tasks.eventtask.dtos.EventDto;
import com.tasks.eventtask.dtos.LocationDto;

public class EventMapperImpl implements EventMapper{

    @Override
    public EventDto toDto(Event src) {
        if (src == null) {
            return null;
        }
        EventDto dto = new EventDto();

        dto.setId(src.getId());
        dto.setTitle(src.getTitle());
        dto.setDescription(src.getDescription());
        
        LocationMapperImpl locationMapper = new LocationMapperImpl();
        LocationDto locationDto = locationMapper.toDto(src.getLocation());

        dto.setLocation(locationDto);
        
        return dto;
    }

    @Override
    public Set<EventDto> toDtos(Iterable<Event> src) {
        if (src == null) {
            return null;
        }
        Set<EventDto> dtos = new HashSet<EventDto>();
        for (Event event : src) {
            dtos.add(toDto(event));
        }
        return dtos;
    }

    @Override
    public Event fromDto(CreateEventDto src) {
        if (src == null) {
            return null;
        }
        Event entity = new Event();

        entity.setTitle(src.getTitle());
        
        if (src.getDescription() != null) {
            if (src.getDescription().trim().isEmpty()) {
                entity.setDescription(null);
            } else {
                entity.setDescription(src.getDescription());
            }
        }

        return entity;
    }
    
    @Override
    public Event fromDtoUpdate(CreateEventDto src, Event entity) {
        if (src == null || entity == null) {
            return null;
        }
        
        if (src.getTitle() != null) {
            entity.setTitle(src.getTitle());
        }
        
        if (src.getDescription() != null) {
            if (src.getDescription().trim().isEmpty()) {
                entity.setDescription(null);
            } else {
                entity.setDescription(src.getDescription());
            }
        }
       
        return entity;
    }
    
}
