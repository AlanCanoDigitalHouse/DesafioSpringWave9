package com.socialmeli.repositories;

import com.socialmeli.models.User;
import com.socialmeli.models.UserSocial;

import java.util.ArrayList;
import java.util.List;

public interface ISocialRepository {

    UserSocial findUserbyId(Integer Id);

    UserSocial saveUser(UserSocial user);


}
