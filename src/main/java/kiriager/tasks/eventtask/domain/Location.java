package kiriager.tasks.eventtask.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
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
  private Set<Event> events = new HashSet<>();

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
    return events;
  }

  public void setEvents(Set<Event> events) {
    this.events = events;
  }

  public boolean isInArea(double lat1, double lng1, double lat2, double lng2) { 
    double top = lat1;
    double bottom = lat2;
    if (lat2 >= lat1) {
      top = lat2;
      bottom = lat1;
    }
    
    double left = lng1;
    double right = lng2;
    if (lng1 >= lng2) {
      left = lng2;
      right = lng1;
    }

    if (lat < top && lat > bottom && lng > left && lng < right) {
      return true;
    }
    return false;
  }

  public static boolean validateCoordinates(double lat, double lng) {
    if (!(lat <= 90 && lat >= -90)) {
      return false;
    }
    if (!(lng <= 180 && lat >= -180)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Location{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", lat=" + lat +
        ", lng=" + lng +
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
