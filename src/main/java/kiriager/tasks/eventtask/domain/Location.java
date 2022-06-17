package kiriager.tasks.eventtask.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String description;
  private double lat;
  private double lng;
  @OneToMany
  @JoinColumn(name = "location_id")
  private Set<Event> Events = new HashSet<>();

  public Location(String title, String description, double lat, double lng) {
    this.title = title;
    this.description = description;
    this.lat = lat;
    this.lng = lng;
  }

  public Location() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public Set<Event> getEvents() {
    return Events;
  }

  public void setEvents(Set<Event> events) {
    Events = events;
  }

  @Override
  public String toString() {
    return "Location{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", lat=" + lat +
        ", lng=" + lng +
        ", Events=" + Events +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Location location = (Location) o;

    return id != null ? id.equals(location.id) : location.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
