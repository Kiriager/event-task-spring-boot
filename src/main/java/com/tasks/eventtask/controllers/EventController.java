package com.tasks.eventtask.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.dtos.AreaDto;
import com.tasks.eventtask.dtos.CreateEventDto;
import com.tasks.eventtask.dtos.EventDto;
import com.tasks.eventtask.mappers.EventMapperImpl;
import com.tasks.eventtask.repositories.EventRepository;
import com.tasks.eventtask.repositories.LocationRepository;


@Controller
public class EventController {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final EventMapperImpl eventMapper;

    public EventController(
        EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        eventMapper = new EventMapperImpl();
    }

    @PostMapping(value = "/events")
    public ResponseEntity<Object> addEvent(@Valid @RequestBody CreateEventDto dto) {
        Optional<Location> location = locationRepository.findById(dto.getLocationId());
        if (!location.isPresent()){
            return new ResponseEntity<>("Location does't exist.", HttpStatus.BAD_REQUEST);
        }
        Event newEvent = eventMapper.fromDto(dto);
        newEvent.setLocation(location.get());

        return new ResponseEntity<>(eventMapper.toDto(newEvent), HttpStatus.OK); 
    }

    @GetMapping(value = "/events")
    public Set<EventDto> getEvents() {
        Iterable<Event> events = eventRepository.findAll();
        Set<EventDto> dtos = eventMapper.toDtos(events);
        return dtos;   
    }

    
    @GetMapping(value = "/events/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id){
        Optional<Event> entity = eventRepository.findById(id);
        if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(eventMapper.toDto(entity.get()), HttpStatus.OK);
    }

    
    @GetMapping(value = "/events/in-location")
    public ResponseEntity<Object> getEventsByLocation(@RequestParam("locationId") Long locationId){

        Optional<Location> location = locationRepository.findById(locationId);
        if (!location.isPresent()){
            return new ResponseEntity<>("Location does't exist.", HttpStatus.BAD_REQUEST);
        }
       
        Set<Event> eventsInLocation = location.get().getEvents();
        if (eventsInLocation.size() < 1) {
            return ResponseEntity.ok().body("There are no events related to the location");
        }

        Set<EventDto> dtos = eventMapper.toDtos(eventsInLocation);
        return ResponseEntity.ok().body(dtos);
    }


    @GetMapping(value = "/events/in-area")
    public ResponseEntity<Object> getEventsInArea(@Valid @RequestBody AreaDto area) {
             
        Iterable<Event> allEvents = eventRepository.findAll();
        HashSet<Event> eventsInArea = new HashSet<>();
        for (Event event : allEvents) {
            Location eventLocation = event.getLocation();
            if (eventLocation.isInArea(area.getLat1(), 
                    area.getLng1(), area.getLat2(), area.getLng2())) {
                eventsInArea.add(event);
            }
        }

        if (eventsInArea.size() <= 0) {
            return ResponseEntity.ok().body("There are no events in the area.");
        } 

        return ResponseEntity.ok().body(eventMapper.toDtos(eventsInArea));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

