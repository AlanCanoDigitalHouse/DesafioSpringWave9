package com.mercadolibre.calculadorametroscuadrados.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class HouseDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacio")
  @Pattern(regexp = "[A-Z](\\p{Alpha}||\\s)+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String name;
  private String address;
  @NotBlank(message = "el barrio no puede estar vacio")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String location;
  @NotEmpty(message = "La lista no puede estar vacia")
  private List<@Valid RoomDTO> rooms;
  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @DecimalMax(value = "4000.0",message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private Integer price;

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public HouseDTO() {

  }

  public HouseDTO(String name, String address, String location, List<@Valid RoomDTO> rooms, Integer price) {
    this.name = name;
    this.address = address;
    this.location = location;
    this.rooms = rooms;
    this.price = price;
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
