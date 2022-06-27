package kiriager.tasks.eventtask.controllers;

import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.repositories.LocationRepository;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController {
  private final LocationRepository locationRepository;

  public LocationController(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }
  
  @RequestMapping("/locations")
  @ResponseBody
  public Iterable<Location> getLocations() {
    return locationRepository.findAll();
  }

  @ResponseBody
  @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
  public ResponseEntity<Location> getEvent(@PathVariable("id") Long id){
    Optional<Location> entity = locationRepository.findById(id);
    if (!entity.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(entity.get());
  }
}
