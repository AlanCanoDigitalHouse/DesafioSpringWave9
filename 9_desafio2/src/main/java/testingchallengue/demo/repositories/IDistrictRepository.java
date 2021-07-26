package testingchallengue.demo.repositories;

import testingchallengue.demo.exceptions.ExistentDistrictNameException;
import testingchallengue.demo.models.District;

public interface IDistrictRepository {

    public District findDistrictByName(String name);
    void addDistrict(District district) throws ExistentDistrictNameException;
}
