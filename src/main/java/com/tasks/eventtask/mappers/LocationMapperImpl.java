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
        
        if (src.getDescription() != null) {
            if (src.getDescription().trim().isEmpty()) {
                entity.setDescription(null);
            } else {
                entity.setDescription(src.getDescription());
            }
        }
        
        entity.setLat(src.getLat());
        entity.setLng(src.getLng());
        
        return entity;
    }

    @Override
    public Location fromDtoUpdate(CreateLocationDto src, Location entity) {
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
        
        if (src.getLat() != null) {
            entity.setLat(src.getLat());
        }
        if (src.getLng() != null) {
            entity.setLng(src.getLng());
        }
       
        return entity;
    }
    
}
