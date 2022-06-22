package kiriager.tasks.eventtask.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kiriager.tasks.eventtask.domain.Event;
import kiriager.tasks.eventtask.repositories.EventRepository;


@Controller
public class EventContoller {

    private final EventRepository eventRepository;

    public EventContoller(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/events")
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

    
}
