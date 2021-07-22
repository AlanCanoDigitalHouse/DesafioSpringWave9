package challenge1springboot.socialmeli.exceptions.post;

import challenge1springboot.socialmeli.exceptions.general.BadRequestException;

public class InvalidPostException extends BadRequestException {
    public InvalidPostException(String message) {
        super(message);
    }
}