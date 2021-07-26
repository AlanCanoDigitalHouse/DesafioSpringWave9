package com.mercadolibre.squaremeter.services;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.dtos.request.District;
import com.mercadolibre.squaremeter.dtos.request.Environment;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoEnvironments;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;
import com.mercadolibre.squaremeter.exceptions.DistrictNotExist;
import com.mercadolibre.squaremeter.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistrictServiceImp implements DistrictService {
    DistrictRepository repo;

    public DistrictServiceImp(DistrictRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DistrictDTO> getAllDistrict() {
        return repo.findDistrictAll();
    }

    @Override
    public InfoHome postHouseReport(Home home) throws DistrictNotExist {
        District dis = home.getDistrict();
        if (!repo.findDistrictByName(dis.getDistrict_name())) {
            throw new DistrictNotExist();
        }
        List<InfoEnvironments> infoEnvironments = calculateMeterByEnvironment(home.getEnvironment());
        return mapResponseHouseReport(infoEnvironments, home.getDistrict().getDistrict_price());
    }

    private InfoHome mapResponseHouseReport(List<InfoEnvironments> infoEnvironments, Double price) {
        InfoHome response = new InfoHome();
        response.setEnvironmentsMeter(infoEnvironments);
        response.setEnvironmentBiggest(calculateBiggestEnvironments(infoEnvironments));
        Double meters = calculateMeter(infoEnvironments);
        response.setSquareMeter(meters);
        response.setPriceAppraisal(calculatePriceHouse(meters, price));
        return response;
    }


    private Double calculatePriceHouse(Double environmentsMeter, Double district_price) {
        return environmentsMeter * district_price;
    }

    private String calculateBiggestEnvironments(List<InfoEnvironments> environmentsMeter) {
        InfoEnvironments res = environmentsMeter.stream()
                .max(Comparator.comparing(InfoEnvironments::getSquare_meter))
                .orElseThrow(NoSuchElementException::new);
        return res.getName();
    }

    private List<InfoEnvironments> calculateMeterByEnvironment(List<Environment> environment) {
        List<InfoEnvironments> response = new ArrayList<>();
        for (Environment t : environment) {
            InfoEnvironments infoEnvironments = new InfoEnvironments();
            Double meters = t.getEnvironment_length() * t.getEnvironment_width();
            infoEnvironments.setSquare_meter(meters);
            infoEnvironments.setName(t.getEnvironment_name());
            response.add(infoEnvironments);
        }

        return response;
    }


    private Double calculateMeter(List<InfoEnvironments> environmentsMeter) {
        return environmentsMeter.stream().mapToDouble(InfoEnvironments::getSquare_meter).sum();
    }
}
