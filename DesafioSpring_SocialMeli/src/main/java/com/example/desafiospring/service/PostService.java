package com.example.desafiospring.service;

import com.example.desafiospring.dtos.FollowDTO;
import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.PromoPostDTO;
import com.example.desafiospring.dtos.request.PostRequestDTO;
import com.example.desafiospring.dtos.request.PromoPostRequestDTO;
import com.example.desafiospring.dtos.response.PostResponseDTO;
import com.example.desafiospring.dtos.response.UserPostsResponseDTO;
import com.example.desafiospring.exceptions.UserNotExistsException;
import com.example.desafiospring.repository.IPostRepository;
import com.example.desafiospring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public PostDTO loadPost(PostRequestDTO pPostRequestDTO) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pPostRequestDTO.getUserId()))
            throw new UserNotExistsException("El usuario " + pPostRequestDTO.getUserId() + " no existe.");

        return postRepository.loadPostDB(pPostRequestDTO);
    }

    @Override
    public List<PostResponseDTO> getPostsByUser(FollowDTO pFollow) {
        return postRepository.getPostLastTwoWeeks(pFollow.getUserId());
    }

    @Override
    public UserPostsResponseDTO getPromoPosts(int pUserId) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pUserId))
            throw new UserNotExistsException("El usuario " + pUserId + " no existe.");

        UserPostsResponseDTO vUserResponseDTO = new UserPostsResponseDTO(
                pUserId,
                userRepository.getUserDB(pUserId).getUserName(),
                postRepository.getPromoPostsById(pUserId).size()
        );

        return vUserResponseDTO;
    }

    @Override
    public PromoPostDTO loadPromoPost(PromoPostRequestDTO pPostRequestDTO) throws UserNotExistsException {

        if (!userRepository.userExsistsDB(pPostRequestDTO.getUserId()))
            throw new UserNotExistsException("El usuario " + pPostRequestDTO.getUserId() + " no existe.");

        return postRepository.loadPromoPostDB(pPostRequestDTO);
    }

}
