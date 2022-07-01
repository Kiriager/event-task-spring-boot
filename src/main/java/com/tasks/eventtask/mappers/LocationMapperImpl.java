package com.tasks.eventtask.mappers;

import java.util.HashSet;
import java.util.Set;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.dtos.CreateLocationDto;
import com.tasks.eventtask.dtos.LocationDto;


public class LocationMapperImpl implements LocationMapper{

    public LocationMapperImpl() {
    }

    @Override
    public LocationDto toDto(Location src) {
        if (src == null) {
            return null;
        }
        LocationDto dto = new LocationDto();

        dto.setId(src.getId());
        dto.setTitle(src.getTitle());
        dto.setDescription(src.getDescription());
        dto.setLat(src.getLat());
        dto.setLng(src.getLng());

        Set<Long> eventsIds = new HashSet<Long>();
        Set<Event> events = src.getEvents();
        
        for (Event event : events) {
            eventsIds.add(event.getId());    
        }
        dto.setEventsIds(eventsIds);
        
        return dto;
    }

    @Override
    public Location fromDto(CreateLocationDto src) {
        if (src == null) {
            return null;
        }
        Location entity = new Location();

        entity.setTitle(src.getTitle());
        entity.setDescription(src.getDescription());

        entity.setLat(Double.parseDouble(src.getLat()));
        entity.setLng(Double.parseDouble(src.getLng()));
        
        return entity;
    }
    
}
