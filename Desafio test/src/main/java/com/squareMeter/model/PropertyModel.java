package com.squareMeter.model;

import com.squareMeter.dto.request.district.DistrictRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertyModel {
    private String prop_name;
    private DistrictRequestDTO district;
    private List<EnvironmentModel> environments;

}
