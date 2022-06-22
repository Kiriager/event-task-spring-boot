package kiriager.tasks.eventtask.controllers;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getEvents(Model model) {
        
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }
    
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public String getEvent(@PathVariable("id") Long id, Model model){
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            return "event";
        }
        return "record-not-found";
    }

    @RequestMapping(value = "/events/in-location/{locationId}", method = RequestMethod.GET)
    public String getEventsByLocation(@PathVariable("locationId") Long locationId, Model model){
       
        Iterable<Event> allEvents = eventRepository.findAll();
        HashSet<Event> eventsInLocation = new HashSet<>();
        for (Event event : allEvents) {
            Location eventLocation = event.getLocation();
            if (eventLocation.getId().equals(locationId)) {
                eventsInLocation.add(event);
            }
        }

        if (eventsInLocation.size() > 0) {
            model.addAttribute("events", eventsInLocation);
            return "events";
        }

        return "record-not-found";
    }

    
}
