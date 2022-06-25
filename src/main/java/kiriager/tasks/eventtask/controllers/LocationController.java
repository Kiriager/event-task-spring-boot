package kiriager.tasks.eventtask.controllers;

import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.repositories.LocationRepository;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocationController {
  private final LocationRepository locationRepository;

  public LocationController(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }
  @RequestMapping("/locations")
  public String getLocations(Model model) {

    model.addAttribute("locations", locationRepository.findAll());
    return "locations";
  }

  @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
  public String getEvent(@PathVariable("id") Long id, Model model){
    Optional<Location> location = locationRepository.findById(id);
    if (location.isPresent()) {
      model.addAttribute("locations", location.get());
      return "locations";
    }
    return "record-not-found";
  }
}
