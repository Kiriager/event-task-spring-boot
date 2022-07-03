package com.tasks.eventtask.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;

public class CreateLocationDto {
    @NotBlank(message = "title is mandatory")
    @Size(min = 2, max = 50, message = "title must be 2-50 charecters long")
    private String title;
    
    @Size(max = 200, message = "description must be less than 200 charecters long")
    private String description;
    
    @NotNull(message = "latitude is mandatory")
    @Latitude
    private Double lat;
    
    @NotNull(message = "longitude is mandatory")
    @Longitude
    private Double lng;

    public CreateLocationDto() {
    }

    public CreateLocationDto(String title, String description, Double lat, Double lng) {
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


    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
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
