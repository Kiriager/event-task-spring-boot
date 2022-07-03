package com.tasks.eventtask.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateEventDto {
    @NotBlank(message = "title is mandatory")
    @Size(min = 2, max = 50, message = "title must be 2-50 charecters long")
    private String title;
    
    @Size(max = 200, message = "description must be less than 200 charecters long")
    private String description;

    @NotNull(message = "location is mandatory")
    private Long locationId;

    public CreateEventDto() {
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

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public CreateEventDto(String title, String description, Long locationId) {
        this.title = title;
        this.description = description;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", locationId='" + getLocationId() + "'" +
            "}";
    }
    
}
