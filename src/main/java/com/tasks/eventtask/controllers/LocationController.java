package com.tasks.eventtask.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.dtos.CreateLocationDto;
import com.tasks.eventtask.dtos.LocationDto;
import com.tasks.eventtask.dtos.UpdateLocationDto;
import com.tasks.eventtask.mappers.LocationMapperImpl;
import com.tasks.eventtask.repositories.LocationRepository;

@RestController
public class LocationController {
  private final LocationRepository locationRepository;
  private final LocationMapperImpl locationMapper;

  public LocationController(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
    locationMapper = new LocationMapperImpl();
  }
  
  @GetMapping("/locations")
  public Set<LocationDto> getLocations() {
    Iterable<Location> locations = locationRepository.findAll();
    Set<LocationDto> dtos = new HashSet<LocationDto>();
    for (Location location : locations) {
      dtos.add(locationMapper.toDto(location));
    }
    return dtos;
  }

  @GetMapping(value = "/locations/{id}")
  public ResponseEntity<LocationDto> getLocation(@PathVariable("id") Long id){
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(locationMapper.toDto(entity.get()));
  }


  @PostMapping("/locations")
  public LocationDto addLocation(@Valid @RequestBody CreateLocationDto dto) {

    Location newLocation = locationMapper.fromDto(dto);
    newLocation = locationRepository.save(newLocation);
    return new LocationMapperImpl().toDto(newLocation);
  }

  @PutMapping(value = "/locations/{id}")
  public ResponseEntity<LocationDto> changeLocation(
        @RequestBody UpdateLocationDto dto, 
        @PathVariable("id") Long id){
    
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Location updatedLocation = locationMapper.fromDtoUpdate(dto, entity.get());
    return ResponseEntity.ok().body(locationMapper.toDto(updatedLocation));
  }
  
  @PutMapping(value = "/location/{id}")
  public ResponseEntity<LocationDto> updateLocation(
        @RequestBody UpdateLocationDto dto, 
        @PathVariable("id") Long id){
    
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Location updatedLocation = locationMapper.fromDtoUpdate(dto, entity.get());
    return ResponseEntity.ok().body(locationMapper.toDto(updatedLocation));
  }

  @DeleteMapping(value = "/locations/{id}")
  public ResponseEntity<String> deleteLocation(@PathVariable("id") Long id){
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    locationRepository.delete(entity.get());
    return ResponseEntity.ok().body("Location id = " + entity.get().getId() + " is deleted");
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
