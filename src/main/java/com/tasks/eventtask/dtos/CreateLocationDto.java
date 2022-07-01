package com.tasks.eventtask.dtos;

import javax.validation.constraints.NotBlank;

public class CreateLocationDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotBlank(message = "Description is mandatory")
    private String description;

    //@NotNull(groups = CreateGroup.class)
    //@Latitude(groups = {CreateGroup.class, UpdateGroup.class})
    private double lat;

    //@NotNull(groups = CreateGroup.class)
   // @Longitude(groups = {CreateGroup.class, UpdateGroup.class})
    private double lng;

    public CreateLocationDto() {
    }

    public CreateLocationDto(String title, String description, double lat, double lng) {
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
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
   /*  
    public interface CreateGroup {
    }
  
    public interface UpdateGroup {
    }
*/

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", lat='" + getLat() + "'" +
            ", lng='" + getLng() + "'" +
            "}";
    }

}
