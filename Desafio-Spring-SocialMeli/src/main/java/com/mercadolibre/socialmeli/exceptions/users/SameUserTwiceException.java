package com.mercadolibre.socialmeli.exceptions.users;

public class SameUserTwiceException extends Exception{

    private Integer id;

    public SameUserTwiceException(Integer id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "You can't send the same id twice: " + this.id.toString();
    }

}
