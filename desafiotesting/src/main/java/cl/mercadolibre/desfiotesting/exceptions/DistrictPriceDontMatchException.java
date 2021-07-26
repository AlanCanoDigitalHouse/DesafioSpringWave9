package cl.mercadolibre.desfiotesting.exceptions;

public class DistrictPriceDontMatchException extends Exception{

    public static final String ERROR = "The price dont match with the district";

    public DistrictPriceDontMatchException(){
        super();
    }

}
