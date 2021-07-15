package com.desafiospring.socialmeli.handlers;

import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.exceptions.InvalidDateException;
import com.desafiospring.socialmeli.exceptions.UserDoesNotExistException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.repositories.IRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidationHandler {

    private static final String DATE_PATTERN = "dd-MM-yyyy";

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

    public static LocalDate validateDate(String dateString) throws UserException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
        return  date;
    }
}
