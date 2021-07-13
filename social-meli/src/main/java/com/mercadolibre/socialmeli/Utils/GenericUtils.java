package com.mercadolibre.socialmeli.Utils;

import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@Component
public class GenericUtils {
    UserRepository repoUser;

    final static LocalDate now = LocalDate.now();

    public GenericUtils(UserRepository repoUser) {
        this.repoUser = repoUser;
    }

    public boolean userIsExist(int user) {
        boolean r = false;
        if (getAllUsers().stream().filter(u -> u.getUserId() == user).findFirst().isPresent()) {
            r = true;
        }
        return r;
    }

    public List<User> getAllUsers() {
        return repoUser.findAllUser();
    }

    public List<User> getOrderUser(String order, List<User> users) {
        List<User> or = users;
        if (order != null) {
            switch (order) {
                case "name_asc":
                    users.sort(Comparator.comparing(User::getUserName));
                    break;
                case "name_desc":
                    users.sort(Comparator.comparing(User::getUserName, Comparator.reverseOrder()));
                    break;

            }
        }
        return or;
    }

    public int getDay(Calendar date) throws ParseException {
        int day = 15;
        Period period = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now);
        if (period.getMonths() == 0) {
            day = period.getDays();
        }
        return day;

    }

    public List<UserToUser> findAllRelationByUserFollowed(int user) {
        return repoUser.findAllRelationByUserFollowed(user);
    }

    public User getUserId(int user) {
        return repoUser.findUserById(user);
    }

    public List<Publication> getOrderPublication(String order, List<Publication> pub) {
        List<Publication> or = pub;
        if (order != null) {
            switch (order) {
                case "date_asc":
                    or.sort(Comparator.comparing(Publication::getDate));
                    break;
                case "date_desc":
                    or.sort(Comparator.comparing(Publication::getDate).reversed());
                    break;
                case "name_asc":
                    or.sort(Comparator.comparing(n -> n.getDetail().getProductName()));
                    break;
                case "name_desc":
                    or.sort(Comparator.comparing(n -> n.getDetail().getProductName(), Comparator.reverseOrder()));
                    break;
            }
        }
        return or;
    }


}
