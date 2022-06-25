package kiriager.tasks.eventtask.bootstrap;

import kiriager.tasks.eventtask.domain.Event;
import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.repositories.EventRepository;
import kiriager.tasks.eventtask.repositories.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final LocationRepository locationRepository;
  private final EventRepository eventRepository;


  public BootStrapData(LocationRepository locationRepository, EventRepository eventRepository) {
    this.locationRepository = locationRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Location location1 = new Location("Huliaypole", "Nice Place", 10.0, 10.0);
    Location location2 = new Location("Chervone", "Nice Village", 12.0, 8.0);
    Event event1 = new Event("Battle", "Rusni ***");
    Event event2 = new Event("Fight", "Still rusni ***");
    Event event3 = new Event("War", "And again rusni ***");
    
    locationRepository.save(location1);
    locationRepository.save(location2);
    eventRepository.save(event1);
    eventRepository.save(event2);
    eventRepository.save(event3);

    location1.getEvents().add(event1);
    location1.getEvents().add(event2);
    location2.getEvents().add(event3);

    event1.setLocation(location1);
    event2.setLocation(location1);
    event3.setLocation(location2);

    locationRepository.save(location1);
    locationRepository.save(location2);
    eventRepository.save(event1);
    eventRepository.save(event2);
    eventRepository.save(event3);

    System.out.println("Hello there!");
    
  }

}
