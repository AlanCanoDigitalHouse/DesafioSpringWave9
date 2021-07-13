package challenge1springboot.socialmeli.services;

import challenge1springboot.socialmeli.util.JSONReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ResponseEntity<HttpStatus> newUserFollow(String userId, String userIdToFollow) {
        // Should see if is an integer
        // should check if the users exists
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
