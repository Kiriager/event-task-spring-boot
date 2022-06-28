package kiriager.tasks.eventtask.dto.location;

import javax.validation.constraints.NotNull;

import kiriager.tasks.eventtask.constraints.Description;
import kiriager.tasks.eventtask.constraints.Title;

public class LocationCreateDto {

    @NotNull(groups = CreateGroup.class)
    @Title(groups = {CreateGroup.class, UpdateGroup.class})
    private String title;
    
    @Description(groups = {CreateGroup.class, UpdateGroup.class})
    private String description;

    @NotNull(groups = CreateGroup.class)
    @Title(groups = {CreateGroup.class, UpdateGroup.class})
    private double lat;

    @NotNull(groups = CreateGroup.class)
    @Title(groups = {CreateGroup.class, UpdateGroup.class})
    private double lng;

    public LocationCreateDto() {
    }

    public LocationCreateDto(String title, String description, double lat, double lng) {
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
    
    public interface CreateGroup {
    }
  
    public interface UpdateGroup {
    }


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

