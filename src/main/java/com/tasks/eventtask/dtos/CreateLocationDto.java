package com.tasks.eventtask.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;

public class CreateLocationDto {
    @NotBlank(message = "title is mandatory")
    @Size(min = 2, max = 50, message = "title must be 2-50 charecters long")
    private String title;
    
    @NotBlank(message = "description is mandatory")
    @Size(min = 6, max = 200, message = "description must be 6-50 charecters long")
    private String description;

    //@NotNull(groups = CreateGroup.class)
    
    @Latitude
    @NotBlank(message = "latitude is mandatory")
    private String lat;

    //@NotNull(groups = CreateGroup.class)
    
    @Longitude
    @NotBlank(message = "longitude is mandatory")
    private String lng;

    public CreateLocationDto() {
    }

    public CreateLocationDto(String title, String description, String lat, String lng) {
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


    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
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
