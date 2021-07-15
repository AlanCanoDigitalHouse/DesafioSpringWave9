package desafiospringw9.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ProductIdNotValidException extends MeliException{

    public ProductIdNotValidException(int productId){
        super("Product #" + productId + " in not valid ", HttpStatus.BAD_REQUEST);
    }
}
