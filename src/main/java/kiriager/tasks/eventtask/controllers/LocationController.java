package kiriager.tasks.eventtask.controllers;

import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.dto.location.LocationCreateDto;
import kiriager.tasks.eventtask.dto.location.LocationDto;
import kiriager.tasks.eventtask.mappers.LocationMapperImpl;
import kiriager.tasks.eventtask.repositories.LocationRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController {
  private final LocationRepository locationRepository;
  private final LocationMapperImpl locationMapper;

  public LocationController(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
    locationMapper = new LocationMapperImpl();
  }
  
  @RequestMapping("/locations")
  @ResponseBody
  public Set<LocationDto> getLocations() {
    Iterable<Location> locations = locationRepository.findAll();
    Set<LocationDto> dtos = new HashSet<LocationDto>();
    
    for (Location location : locations) {
      dtos.add(locationMapper.toDto(location));
    }
    return dtos;
  }

  @ResponseBody
  @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
  public ResponseEntity<LocationDto> getEvent(@PathVariable("id") Long id){
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok().body(locationMapper.toDto(entity.get()));
  }

  @ResponseBody
  @PostMapping("/locations")
  public LocationDto addLocation(
        @Validated(LocationCreateDto.CreateGroup.class) @RequestBody LocationCreateDto dto) {
    System.out.println(dto);
    Location newLocation = new Location(
        dto.getTitle(), dto.getDescription(), dto.getLat(), dto.getLng());
    
    newLocation = locationRepository.save(newLocation);
    return new LocationMapperImpl().toDto(newLocation);
  }
  
}
