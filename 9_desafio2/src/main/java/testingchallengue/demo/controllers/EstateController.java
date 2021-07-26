package testingchallengue.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testingchallengue.demo.dtos.DistrictDTO;
import testingchallengue.demo.dtos.EstateAssessmentDTO;
import testingchallengue.demo.dtos.EstateDTO;
import testingchallengue.demo.exceptions.DistrictNotFoundException;
import testingchallengue.demo.exceptions.ExistentDistrictNameException;
import testingchallengue.demo.service.IEstateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/estate")

public class EstateController {
    @Autowired
    IEstateService estateService;

    @PostMapping("/assessment")
    public ResponseEntity<EstateAssessmentDTO> getAssessment(@Valid @RequestBody EstateDTO house) throws DistrictNotFoundException{
        return new ResponseEntity<>(estateService.getAssessment(house), HttpStatus.OK);
    }

    @PostMapping("/new/district")
    public ResponseEntity<String> addDistrict(@Valid @RequestBody DistrictDTO district) throws ExistentDistrictNameException{
        return new ResponseEntity<>(estateService.addDistrict(district), HttpStatus.OK);
    }
}
