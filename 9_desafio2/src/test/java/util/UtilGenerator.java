package util;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


//Generador de caso de pruebas para test
public class UtilGenerator {

    private static final List<RoomDTO> roomList = new ArrayList<>(
            Arrays.asList(
                    new RoomDTO("Baño",2,3),
                    new RoomDTO("Cocinaliving",3,6),
                    new RoomDTO("Dormitorio",4,4)
            )
    );

    private static final Double price = 3000.0;


    //HouseDTO Perfect payload
    public static HouseDTO genHousePerfectCase(){
        HouseDTO house = new HouseDTO("Casa", "Santiago", price ,roomList);
        house.setDistrict_price(3000.0);
        return house;
    }

    public static HouseResponseDTO genHouseResponseExpected(HouseDTO house){
        HouseResponseDTO expected = new HouseResponseDTO(house);
        expected.setBiggest(house.getEnviroments().get(1));
        expected.setPrice(120000.0);
        expected.setSquareFeet(40);
        return expected;
    }

    //Validaciones nombre propiedad en payload
    public static HouseDTO genHouseNameEmpty(){
        HouseDTO house = new HouseDTO("", "Santiago", price,roomList);
        return house;
    }

    public static HouseDTO genHouseNameNonCapitalize(){
        HouseDTO house = new HouseDTO("casa Colores", "Santiago", price,roomList);
        return house;
    }

    public static HouseDTO genHouseNameSizeGT30(){
        HouseDTO house = new HouseDTO("Casaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Santiago", price,roomList);
        return house;
    }

    //Validaciones direccion propiedad en payload
    public static HouseDTO genHouseAddressEmpty(){
        HouseDTO house = new HouseDTO("Casa Colores", "", price,roomList);
        return house;
    }

    public static HouseDTO genHouseAddressSizeGT45(){
        HouseDTO house = new HouseDTO(
                "Casa Colores",
                "Santiagooooooooooooooooooooooooooooooooooooooo",
                price,
                roomList);
        return house;
    }


    //Validaciones rooms
    public static HouseDTO genHouseEmptyRooms(String name, String address){
        List<RoomDTO> roomList = new ArrayList<>();
        HouseDTO house = new HouseDTO(name, address, price, roomList);
        return house;
    }

}
