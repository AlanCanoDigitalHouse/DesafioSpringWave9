package desafiospringw9.demo.exceptions;

import desafiospringw9.demo.dtos.ErrorDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MeliException extends Exception {
    private ErrorDTO error;
    private HttpStatus status;

    public MeliException(String message, HttpStatus status) {
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setName(this.getClass().getSimpleName());
        this.status = status;
    }
}
