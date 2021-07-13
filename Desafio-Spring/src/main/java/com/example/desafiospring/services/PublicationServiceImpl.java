package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.Publication;
import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.repositories.PublicationRepositoryImpl;
import com.example.desafiospring.repositories.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements IPublicationService{

    private final PublicationRepositoryImpl publicationRepository;
    private final UserRepositoryImpl userRepository;

    public PublicationServiceImpl(PublicationRepositoryImpl publicationRepository, UserRepositoryImpl userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<?> newPost(Integer userId, PublicationRequestDTO publicationRequestDTO) throws UserNotFindException {
        UserInfo user = userRepository.getUser(userId);
        Publication postToSave = new Publication();
        if(user != null){
            List<Publication> myPosts = publicationRepository.getPosts(userId);
            postToSave.setDate( publicationRequestDTO.getDate() );
            postToSave.setDetail( publicationRequestDTO.getDetail().parseToProduct() );
            postToSave.setCategory(publicationRequestDTO.getCategory());
            postToSave.setPrice(publicationRequestDTO.getPrice());
            postToSave.getDetail().setProductId(myPosts.size()+1);
            postToSave.setPostId(myPosts.size()+1);
            myPosts.add(postToSave);
            publicationRepository.updatePublicationFile();
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            throw new UserNotFindException();
        }
    }

    @Override
    public List<Publication> getAllPost(Integer userId) {
        return null;
    }
}
