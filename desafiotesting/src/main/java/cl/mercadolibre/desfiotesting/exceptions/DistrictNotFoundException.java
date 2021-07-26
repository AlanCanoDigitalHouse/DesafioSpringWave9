package cl.mercadolibre.desfiotesting.exceptions;

public class DistrictNotFoundException extends Exception{

    public final static String ERROR = "District Not Found check price or name";

    public DistrictNotFoundException(){
        super();
    }

}
