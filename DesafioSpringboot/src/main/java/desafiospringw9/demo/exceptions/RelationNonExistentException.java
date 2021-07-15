package desafiospringw9.demo.exceptions;

import org.springframework.http.HttpStatus;

public class RelationNonExistentException extends MeliException{
    public RelationNonExistentException(int follower, int followed) {
        super("Relation between user #" + follower + "and user #" + followed +"  does not exist ", HttpStatus.BAD_REQUEST);
    }
}
