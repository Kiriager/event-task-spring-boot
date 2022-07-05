package com.tasks.eventtask.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;
import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.dtos.CreateEventDto;
import com.tasks.eventtask.dtos.EventDto;
import com.tasks.eventtask.mappers.EventMapperImpl;
import com.tasks.eventtask.repositories.EventRepository;
import com.tasks.eventtask.repositories.LocationRepository;
import com.tasks.eventtask.services.Area;

@RestController
@Validated
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
    if (!location.isPresent()) {
      return new ResponseEntity<>("Location doesn't exist.", HttpStatus.BAD_REQUEST);
    }
    Event newEvent = eventMapper.fromDto(dto);
    newEvent.setLocation(location.get());
    newEvent = eventRepository.save(newEvent);
    return new ResponseEntity<>(eventMapper.toDto(newEvent), HttpStatus.OK);
  }

  @PutMapping(value = "/events/{id}")
  public ResponseEntity<Object> updateEvent(
        @Validated(CreateEventDto.UpdateGroup.class) @RequestBody CreateEventDto dto, 
        @PathVariable("id") Long id) {

    Optional<Event> event = eventRepository.findById(id);
    if (!event.isPresent()) {
      return new ResponseEntity<>("Event doesn't exist.", HttpStatus.BAD_REQUEST);
    }
    Event updatedEvent = eventMapper.fromDtoUpdate(dto, event.get());
    
    if (dto.getLocationId() != null){
      Optional<Location> location = locationRepository.findById(dto.getLocationId());
      if (!location.isPresent()) {
        return new ResponseEntity<>("Location doesn't exist.", HttpStatus.BAD_REQUEST);
      } else {
        updatedEvent.setLocation(location.get());
      }
    }
    return new ResponseEntity<>(eventMapper.toDto(updatedEvent), HttpStatus.OK);
  }

  @GetMapping(value = "/events")
  public Set<EventDto> getEvents() {
    Iterable<Event> events = eventRepository.findAll();
    Set<EventDto> dtos = eventMapper.toDtos(events);
    return dtos;
  }

  @GetMapping(value = "/events/{id}")
  public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id) {
    Optional<Event> entity = eventRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(eventMapper.toDto(entity.get()), HttpStatus.OK);
  }

  @GetMapping(value = "/events/in-location")
  public ResponseEntity<Object> getEventsByLocation(@RequestParam("locationId") Long locationId) {

    Optional<Location> location = locationRepository.findById(locationId);
    if (!location.isPresent()) {
      return new ResponseEntity<>("Location doesn't exist.", HttpStatus.BAD_REQUEST);
    }

    Set<Event> eventsInLocation = location.get().getEvents();
    if (eventsInLocation.size() < 1) {
      return ResponseEntity.ok().body("There are no events related to the location");
    }

    Set<EventDto> dtos = eventMapper.toDtos(eventsInLocation);
    return ResponseEntity.ok().body(dtos);
  }

  @GetMapping(value = "/events/test")
  public ResponseEntity<String> paramValidationTest(
        @Valid @RequestParam("value") @Latitude Double value) {
    System.out.println(value);
    return ResponseEntity.ok().body(value.toString());
  }

  @GetMapping(value = "/events/in-area")
  public ResponseEntity<Object> getEventsInArea(
        @RequestParam("lat1") @Latitude @NotNull(message = "lat1 is requaired") Double lat1,
        @RequestParam("lng1") @Longitude @NotNull(message = "lng1 is requaired") Double lng1,
        @RequestParam("lat2") @Latitude @NotNull(message = "lat2 is requaired") Double lat2,
        @RequestParam("lng2") @Longitude @NotNull(message = "lng2 is requaired") Double lng2) {
    Area area = new Area(lat1, lng1, lat2, lng2);

    Iterable<Event> allEvents = eventRepository.findAll();
    HashSet<Event> eventsInArea = new HashSet<>();
    for (Event event : allEvents) {
      Location eventLocation = event.getLocation();
      if (eventLocation.isInArea(area)) {
        eventsInArea.add(event);
      }
    }

    if (eventsInArea.size() <= 0) {
      return ResponseEntity.ok().body("There are no events in the area.");
    }

    return ResponseEntity.ok().body(eventMapper.toDtos(eventsInArea));
  }

  @DeleteMapping(value = "/events/{id}")
  public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id){
    Optional<Event> entity = eventRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    eventRepository.delete(entity.get());
    return ResponseEntity.ok().body("Event id = " + entity.get().getId() + " is deleted");
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public Map<String, String> handleConstraintViolationException(
      ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
      errors.put(cv.getMessage(),
          cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString());
    }
    return errors;
  }
}
