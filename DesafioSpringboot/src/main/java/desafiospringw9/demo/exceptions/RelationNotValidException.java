package desafiospringw9.demo.exceptions;

import org.springframework.http.HttpStatus;

public class RelationNotValidException extends MeliException {

    public RelationNotValidException(int follower, int following) {
        super("Relation between user #" + follower + "and user #" + following + " is not valid, or is already existing",
                HttpStatus.BAD_REQUEST);
    }
}
