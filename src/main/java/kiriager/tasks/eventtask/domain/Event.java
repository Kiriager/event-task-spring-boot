package kiriager.tasks.eventtask.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String description;

  @ManyToOne
  private Location location;

  @ManyToMany(mappedBy = "events")
  private Set<Participant> participants = new HashSet<>();

  public Event(String title, String description){
    this.title = title;
    this.description = description;
  }

  public Event() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", location=" + location +
        ", participants=" + participants +
        '}';
  }

  public Set<Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(Set<Participant> participants) {
    this.participants = participants;
  }
}
