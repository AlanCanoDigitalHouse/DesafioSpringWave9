package testingchallengue.demo.service;

import testingchallengue.demo.dtos.DistrictDTO;
import testingchallengue.demo.dtos.EstateAssessmentDTO;
import testingchallengue.demo.dtos.EstateDTO;
import testingchallengue.demo.exceptions.DistrictNotFoundException;
import testingchallengue.demo.exceptions.ExistentDistrictNameException;

public interface IEstateService {

    public EstateAssessmentDTO getAssessment(EstateDTO estate) throws DistrictNotFoundException;

    String addDistrict(DistrictDTO district) throws ExistentDistrictNameException;
}
