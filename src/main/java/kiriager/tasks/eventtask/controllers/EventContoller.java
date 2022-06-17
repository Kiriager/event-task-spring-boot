package kiriager.tasks.eventtask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kiriager.tasks.eventtask.repositories.EventRepository;

@Controller
public class EventContoller {

    private final EventRepository eventRepository;

    public EventContoller(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/events")
    public String getEvents(Model model) {
        System.out.println("Hello from controller");
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }
}
