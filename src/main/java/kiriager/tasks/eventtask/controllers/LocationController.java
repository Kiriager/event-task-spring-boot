package kiriager.tasks.eventtask.controllers;

import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.repositories.LocationRepository;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  @ResponseBody
  @RequestMapping("/locations")
  public Iterable<Location> getLocations(Model model) {
    return locationRepository.findAll();
  }

  @ResponseBody
  @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
  public Location getEvent(@PathVariable("id") Long id){
    return locationRepository.findById(id).get();
  }
}
