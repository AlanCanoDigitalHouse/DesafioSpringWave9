package com.meli.socialmeli.exceptions;

import com.meli.socialmeli.dto.response.ResponseErrorDTO;
import org.springframework.http.HttpStatus;

public class SocialMeliExceptions extends Exception{

    private ResponseErrorDTO errorDTO;
    private HttpStatus httpStatus;

        public SocialMeliExceptions(String mensaje, HttpStatus httpStatus) {
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
            return "MeliToolsException{" +
                    "errorDTO=" + errorDTO +
                    ", httpStatus=" + httpStatus +
                    '}';
        }

}
