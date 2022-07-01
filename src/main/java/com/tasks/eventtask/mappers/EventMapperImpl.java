package com.tasks.eventtask.mappers;

import com.tasks.eventtask.domain.Event;
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
    
}
