package com.tasks.eventtask.dtos;

public class EventDto {
    
    private Long id;
    private String title;
    private String description;
    private LocationDto location;

    public EventDto() {
    }

    public EventDto(Long id, String title, String description, LocationDto location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
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

    public LocationDto getLocation() {
        return this.location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}

   
