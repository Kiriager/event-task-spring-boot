package kiriager.tasks.eventtask.controllers;

import kiriager.tasks.eventtask.repositories.LocationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
