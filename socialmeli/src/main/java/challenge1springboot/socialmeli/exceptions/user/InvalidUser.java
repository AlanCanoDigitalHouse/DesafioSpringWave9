package challenge1springboot.socialmeli.exceptions.user;

import java.io.IOException;

public class InvalidUser extends IOException {
    public InvalidUser(String message) {
        super(message);
    }
}
