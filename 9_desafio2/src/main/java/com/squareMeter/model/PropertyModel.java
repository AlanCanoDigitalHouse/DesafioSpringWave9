package com.squareMeter.model;

import com.squareMeter.dto.request.district.DistrictRequestDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PropertyModel {
    private String prop_name;
    private DistrictRequestDTO district;
    private List<EnvironmentModel> environments;

}
