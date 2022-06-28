package kiriager.tasks.eventtask.dto.location;

import java.util.Set;

public class LocationDto {
    private Long id;
    private String title;
    private String description;
    private double lat;
    private double lng;
    private Set<Long> eventsIds;

    public LocationDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setEventsIds(Set<Long> eventsIds) {
        this.eventsIds = eventsIds;
    }

    public Set<Long> getEventsIds() {
        return eventsIds;
    }

  
}
