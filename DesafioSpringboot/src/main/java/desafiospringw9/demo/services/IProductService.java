package desafiospringw9.demo.services;

import desafiospringw9.demo.dtos.FollowedDTO;
import desafiospringw9.demo.dtos.PostDTO;
import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;




public interface IProductService {

    void createPost(PostDTO post) throws ProductIdNotValidException, PostIdNotValidExceptions, UserNotValidException;

    FollowedDTO getFollowedPosts(int userId, String order, int daysBefore) throws UserNotValidException;

    PostDTO getPostsByUserId(int userId, String filter) throws UserNotValidException;
}
