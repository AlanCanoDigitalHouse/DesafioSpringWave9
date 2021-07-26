package com.example.demo.Services;

import com.example.demo.DTOs.PropertyDTO;
import com.example.demo.DTOs.PropertyDetailsDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;

public interface IPropertyService {

    PropertyDetailsDTO getDetails(PropertyDTO estate) throws CustomExceptionHandler;

}
