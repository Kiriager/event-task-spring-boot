package com.tasks.eventtask.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasks.eventtask.domain.Event;
import com.tasks.eventtask.domain.Location;
import com.tasks.eventtask.repositories.EventRepository;
import com.tasks.eventtask.repositories.LocationRepository;



@Controller
public class EventController {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventController(
        EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Event> getEvents() {   
        return eventRepository.findAll();
    }
    
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable("id") Long id){
        Optional<Event> entity = eventRepository.findById(id);
        if (!entity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/events/in-location", method = RequestMethod.GET)
    public ResponseEntity<Object> getEventsByLocation(@RequestParam("locationId") Long locationId){
        Optional<Location> location = locationRepository.findById(locationId);
        if (!location.isPresent()){
            return new ResponseEntity<>("Location does't exist.", HttpStatus.BAD_REQUEST);
        }
       
        Set<Event> eventsInLocation = location.get().getEvents();
        if (eventsInLocation.size() <= 0) {
            return ResponseEntity.ok().body("Thera are no events related to the location");
        } 
        
        return ResponseEntity.ok().body(eventsInLocation);
    }

    @RequestMapping(value = "/events/in-area", method = RequestMethod.GET)
    public ResponseEntity<Object> getEventsInArea(@RequestParam double lat1,
        @RequestParam double lng1, @RequestParam double lat2, 
        @RequestParam double lng2) {
        
        if (!(Location.validateCoordinates(lat1, lng1)
                && Location.validateCoordinates(lat2, lng2))) {
            return new ResponseEntity<>("Invalid coordinates.", HttpStatus.BAD_REQUEST);
        }
       
        Iterable<Event> allEvents = eventRepository.findAll();
        HashSet<Event> eventsInArea = new HashSet<>();
        for (Event event : allEvents) {
            Location eventLocation = event.getLocation();
            if (eventLocation.isInArea(lat1, lng1, lat2, lng2)) {
                eventsInArea.add(event);
            }
        }

        if (eventsInArea.size() <= 0) {
            return ResponseEntity.ok().body("There are no events in the area.");
        } 

        return ResponseEntity.ok().body(eventsInArea);
    }
}

