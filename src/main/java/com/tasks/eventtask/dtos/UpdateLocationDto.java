package com.tasks.eventtask.dtos;

import javax.validation.constraints.Size;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;

public class UpdateLocationDto {
 
  //@Size(min = 2, max = 50, message = "title must be 2-50 charecters long")
  private String title;
  
  //@Size(max = 200, message = "description must be less than 200 charecters long")
  private String description;
  
  //@Latitude
  private Double lat;
  
  //@Longitude
  private Double lng;

  public UpdateLocationDto() {
  }

  public UpdateLocationDto(String title, String description, Double lat, Double lng) {
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
