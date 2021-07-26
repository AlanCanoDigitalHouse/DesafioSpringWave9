package com.meli.bootcamp.model;

import com.meli.bootcamp.dto.request.PropertyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Property {
    private String property_name;
    private List<Environment> environments;
    private Double area_property;
    private Double valuation_property;

    public Property(PropertyDTO newProperty){
        this.property_name= newProperty.getProp_name();
        environments=new ArrayList<>();
        environments =
                newProperty.getEnvironments().stream().map(Environment::new).collect(Collectors.toList());
        calculateArea();

    }
    public Environment biggestEnvironment(){
            AtomicReference<Environment> biggest = new AtomicReference<>(environments.get(0));
        environments.forEach(environment -> {
            if(environment.getEnvironment_area()> biggest.get().getEnvironment_area()){
                biggest.set(environment);
            }});
        return biggest.get();
    }

    private  void calculateArea(){
        area_property = 0.0;
        environments.forEach(environment -> area_property+=environment.getEnvironment_area());
    }

    public void calculateValuation(Double valuation){
        this.valuation_property =  area_property*valuation;
    }

    public String getProperty_name() {
        return property_name;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    public Double getArea_property() {
        return area_property;
    }

    public Double getValuation_property() {
        return valuation_property;
    }
}


