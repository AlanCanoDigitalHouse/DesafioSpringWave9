package challenge1springboot.socialmeli.exceptions.user;

import challenge1springboot.socialmeli.exceptions.general.BadRequestException;

public class InvalidUserException extends BadRequestException {
    public InvalidUserException(String message) {
        super(message);
    }
}
