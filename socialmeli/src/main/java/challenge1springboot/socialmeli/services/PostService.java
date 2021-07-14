package challenge1springboot.socialmeli.services;

import challenge1springboot.socialmeli.DTO.request.PostRequestDTO;
import challenge1springboot.socialmeli.exceptions.post.InvalidPostException;
import challenge1springboot.socialmeli.exceptions.user.InvalidUserException;
import challenge1springboot.socialmeli.repositories.PostRepository;
import challenge1springboot.socialmeli.repositories.UserRepository;
import challenge1springboot.socialmeli.utils.DateValidator;
import challenge1springboot.socialmeli.globalconstants.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PostService {

    PostRepository postRepository = new PostRepository();
    UserRepository userRepository = new UserRepository();

    public ResponseEntity<HttpStatus> newPost(PostRequestDTO postRequestDTO) {
        if (!DateValidator.validDate(postRequestDTO.getDate()))
            throw new InvalidPostException(Message.NO_VALID_DATE);
        if (Objects.isNull(userRepository.findById(postRequestDTO.getUserId())))
            throw new InvalidUserException(Message.USER_NOT_EXIST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public int testPost() {
        return postRepository.generateID();
    }


}
/*
{
        "userId": 12,
        "date" : "29-4-2021",
        "detail":{
        "productName" : "Silla Gamer",
        "type" : "Gamer",
        "brand" : "Racer",
        "color" : "Red & Black",
        "notes" : "Special Edition"
        },
        "category" : 0,
        "price" : 1500.50
        }*/
