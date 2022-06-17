package kiriager.tasks.eventtask.bootstrap;

import kiriager.tasks.eventtask.domain.Event;
import kiriager.tasks.eventtask.domain.Location;
import kiriager.tasks.eventtask.domain.Participant;
import kiriager.tasks.eventtask.repositories.EventRepository;
import kiriager.tasks.eventtask.repositories.LocationRepository;
import kiriager.tasks.eventtask.repositories.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final LocationRepository locationRepository;
  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;


  public BootStrapData(ParticipantRepository participantRepository,
                       LocationRepository locationRepository, EventRepository eventRepository) {
    //this.userRepository = userRepository;
    this.locationRepository = locationRepository;
    this.eventRepository = eventRepository;
    this.participantRepository = participantRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Location location1 = new Location("Huliaypole", "Nice Place", 10.0, 10.0);
    Event event1 = new Event("Battle", "Rusni ***");
    Event event2 = new Event("Fight", "Still rusni ***");
    Participant participant1 = new Participant("Honey Badger");

    participantRepository.save(participant1);
    locationRepository.save(location1);
    eventRepository.save(event1);
    eventRepository.save(event2);

    participant1.getEvents().add(event1);
    participant1.getEvents().add(event2);

    event1.getParticipants().add(participant1);
    event2.getParticipants().add(participant1);

    location1.getEvents().add(event1);
    location1.getEvents().add(event2);

    event1.setLocation(location1);
    event2.setLocation(location1);

    participantRepository.save(participant1);
    locationRepository.save(location1);
    eventRepository.save(event1);
    eventRepository.save(event2);

    System.out.println("Hello there!");
    System.out.println(participantRepository.count());
  }

}
