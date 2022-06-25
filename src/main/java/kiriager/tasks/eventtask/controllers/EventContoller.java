package kiriager.tasks.eventtask.controllers;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kiriager.tasks.eventtask.domain.Event;
import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.repositories.EventRepository;



@Controller
public class EventContoller {

    private final EventRepository eventRepository;

    public EventContoller(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Event> getEvents() {   
        return eventRepository.findAll();
    }
    
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional getEvent(@PathVariable("id") Long id){
        return eventRepository.findById(id);
    }
    
    @RequestMapping(value = "/events/in-location/{locationId}", method = RequestMethod.GET)
    public HashSet<Event> getEventsByLocation(@PathVariable("locationId") Long locationId){
       
        Iterable<Event> allEvents = eventRepository.findAll();
        HashSet<Event> eventsInLocation = new HashSet<>();
        for (Event event : allEvents) {
            Location eventLocation = event.getLocation();
            if (eventLocation.getId().equals(locationId)) {
                eventsInLocation.add(event);
            }
        }

       return eventsInLocation;
    }

    @RequestMapping(value = "/events/in-area", method = RequestMethod.GET)
    public HashSet<Event> getEventsInArea(@RequestParam double lat1,
        @RequestParam double lng1, @RequestParam double lat2, 
        @RequestParam double lng2) {
       
        Iterable<Event> allEvents = eventRepository.findAll();
        HashSet<Event> eventsInArea = new HashSet<>();
        for (Event event : allEvents) {
            Location eventLocation = event.getLocation();
            if (eventLocation.isInArea(10, 10, 10, 10)) {
                eventsInArea.add(event);
            }
        }

        return eventsInArea;
    }
}
