package com.desafiospring.socialmeli.handlers;

import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.exceptions.InvalidDateException;
import com.desafiospring.socialmeli.exceptions.UserDoesNotExistException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.repositories.IRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationHandler {

    private static final String pattern = "dd-mm-yyyy";

    public static Seller validateSeller(int userIdToFollow, IRepository repository) throws UserException {
        Seller seller = (Seller) repository.get(userIdToFollow);
        if (seller == null) {
            throw new UserDoesNotExistException(userIdToFollow, true);
        }
        return seller;
    }

    public static Buyer validateUser(int userId, IRepository repository) throws UserException {
        Buyer buyer = (Buyer) repository.get(userId);
        if (buyer == null) {
            throw new UserDoesNotExistException(userId, false);
        }
        return buyer;
    }

    public static Date validateDate(String dateString) throws UserException {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
        return date;

    }
}
