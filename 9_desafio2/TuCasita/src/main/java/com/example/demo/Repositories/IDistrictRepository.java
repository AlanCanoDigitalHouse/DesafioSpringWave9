package com.example.demo.Repositories;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Models.District;

public interface IDistrictRepository {

    District findDistrictByName(String name) throws CustomExceptionHandler;

}
