package challenge1springboot.socialmeli.exceptions.general;

public abstract class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
