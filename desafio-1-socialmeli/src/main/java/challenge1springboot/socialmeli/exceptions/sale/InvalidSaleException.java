package challenge1springboot.socialmeli.exceptions.sale;

import challenge1springboot.socialmeli.exceptions.general.BadRequestException;

public class InvalidSaleException extends BadRequestException {
    public InvalidSaleException(String message) {
        super(message);
    }
}
