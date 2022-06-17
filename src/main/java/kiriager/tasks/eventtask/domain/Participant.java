package kiriager.tasks.eventtask.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Participant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ManyToMany
  @JoinTable(name = "event_participant", joinColumns = @JoinColumn(name = "participant_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id"))
  private Set<Event> events = new HashSet<>();

  public Participant() {

  }

  public Participant(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Event> getEvents() {
    return events;
  }

  public void setEvents(Set<Event> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Participant that = (Participant) o;

    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Participant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", events=" + events +
        '}';
  }
}
