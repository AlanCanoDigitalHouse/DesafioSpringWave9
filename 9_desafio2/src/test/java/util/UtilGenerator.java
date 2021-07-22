package util;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Generador de caso de pruebas para test
public class UtilGenerator {

    private static final List<RoomDTO> roomList = new ArrayList<>(
            Arrays.asList(
                    new RoomDTO("Baño",2,3),
                    new RoomDTO("Cocina - living",3,6),
                    new RoomDTO("Dormitorio",4,4)
            )
    );

    //HouseDTO Perfect payload
    public static HouseDTO genHousePerfectCase(String name, String address){
        String nameCapitalize = name.substring(0, 1).toUpperCase() + name.substring(1);
        String addressCapitalize = address.substring(0, 1).toUpperCase() + address.substring(1);
        HouseDTO house = new HouseDTO(nameCapitalize, addressCapitalize, roomList);
        return house;
    }

    //Validaciones nombre propiedad en payload
    public static HouseDTO genHouseNameEmpty(){
        HouseDTO house = new HouseDTO("", "Santiago", roomList);
        return house;
    }

    public static HouseDTO genHouseNameNonCapitalize(){
        HouseDTO house = new HouseDTO("casa Colores", "Santiago", roomList);
        return house;
    }

    public static HouseDTO genHouseNameSizeGT30(){
        HouseDTO house = new HouseDTO("Casaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Santiago", roomList);
        return house;
    }

    //Validaciones direccion propiedad en payload
    public static HouseDTO genHouseAddressEmpty(){
        HouseDTO house = new HouseDTO("Casa Colores", "", roomList);
        return house;
    }

    public static HouseDTO genHouseAddressSizeGT45(){
        HouseDTO house = new HouseDTO(
                "Casa Colores",
                "Santiagooooooooooooooooooooooooooooooooooooooo",
                roomList);
        return house;
    }


    public static HouseDTO genHouseEmptyRooms(String name, String address){
        List<RoomDTO> roomList = new ArrayList<>();
        HouseDTO house = new HouseDTO(name, address, roomList);
        return house;
    }
}
