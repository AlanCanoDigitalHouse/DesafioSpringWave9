package com.meli.joescaos.desafiotesting.dto;

import java.util.List;

public class HouseDTO {
  private String name;
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
