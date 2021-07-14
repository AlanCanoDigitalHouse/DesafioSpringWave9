package desafiospringw9.demo.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotValidException extends MeliException{

    public UserNotValidException(int userId){
        super("UserID #" + userId + " is not valid ", HttpStatus.BAD_REQUEST);
    }
}
