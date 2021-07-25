package com.meli.tucasita.exception;

import com.meli.tucasita.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;

public class CasaException extends Exception{

    private ResponseErrorDTO errorDTO;
    private HttpStatus httpStatus;

    public CasaException(String mensaje, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorDTO = new ResponseErrorDTO(this.getClass().getSimpleName(), mensaje);
    }

    public ResponseErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ResponseErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "CasaException{" +
                "errorDTO=" + errorDTO +
                ", httpStatus=" + httpStatus +
                '}';
    }

}
