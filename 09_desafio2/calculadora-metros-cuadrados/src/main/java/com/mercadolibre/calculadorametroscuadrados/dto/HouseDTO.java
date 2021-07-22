package com.mercadolibre.calculadorametroscuadrados.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class HouseDTO {

  @NotBlank(message = "El nombre no puede estar vacio")
  @Pattern(regexp = "[A-Z](\\p{Alpha}||\\s)+", message = "El nombre debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre del estudiante no puede superar los 30 caracteres.")
  private String name;
  @NotBlank(message = "El nombre del barrio no puede estar vacio")
  @Size(max = 45, message = "La longitud de localidad no puede superar los 45 caracteres.")
  private String address;
  private List<RoomDTO> rooms;
  private String location;

  public HouseDTO() {

  }

  public HouseDTO(String name, String address, List<RoomDTO> rooms, String location){
    this.name = name;
    this.address = address;
    this.rooms = rooms;
    this.location = location;
  }

  public String getLocation(){
    return location;
  }

  public void setLocation(String location){
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<RoomDTO> getRooms() {
    return rooms;
  }

  public void setRooms(List<RoomDTO> rooms) {
    this.rooms = rooms;
  }
}
