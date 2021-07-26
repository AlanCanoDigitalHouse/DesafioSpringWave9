package com.mercadolibre.calculadorametroscuadrados.repositories;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictRepositoryTest {

    @Test
    void getInstance() {
        assertNotNull(DistrictRepository.getInstance());
    }

    @Test
    void initializeNormally() {
        DistrictRepository repo = DistrictRepository.getInstance();
        assertNotEquals(0,repo.getDistrictList().size());
    }
    @Test
    void initializeAndCheckCreated() {
        DistrictRepository repo = DistrictRepository.getInstance();
        Boolean res =  repo.districtList.contains("Malvin") || repo.districtList.contains("Carrasco") || repo.districtList.contains("Punta gorda")
                || repo.districtList.contains("Malvin") || repo.districtList.contains("Carrasco") || repo.districtList.contains("Punta gorda");

        assertEquals(true,res);
    }

    @Test
    void existDistrictExist() {
        DistrictRepository repo = DistrictRepository.getInstance();
        assertEquals(true, repo.existDistrict("Malvin"));
    }
    @Test
    void existDistrictNotExist() {
        DistrictRepository repo = DistrictRepository.getInstance();
        assertEquals(false, repo.existDistrict("Nidas"));
    }

    @Test
    void getDistrictListOnInnit() {
        DistrictRepository repo = DistrictRepository.getInstance();

        List<String> comparable = new ArrayList<String>();
        comparable.add("Malvin");
        comparable.add("Carrasco");
        comparable.add("Pocitos");
        comparable.add("Punta carretas");
        comparable.add("Punta gorda");
        comparable.add("Blanqueada");

        Boolean res = repo.getDistrictList().equals(comparable);

        assertEquals(true, res);

    }
}