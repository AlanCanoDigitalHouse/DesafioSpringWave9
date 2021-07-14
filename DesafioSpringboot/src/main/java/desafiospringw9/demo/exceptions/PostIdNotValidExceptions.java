package desafiospringw9.demo.exceptions;

import org.springframework.http.HttpStatus;

public class PostIdNotValidExceptions extends MeliException{
    public PostIdNotValidExceptions(int postId){
        super("Post #" + postId + " is not valid ", HttpStatus.BAD_REQUEST);
    }
}
