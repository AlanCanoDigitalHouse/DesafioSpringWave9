package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.Publication;
import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowerPostListDTO;
import com.example.desafiospring.dtos.response.UserDTO;
import com.example.desafiospring.exceptions.UserNotFindOrEqualException;
import com.example.desafiospring.repositories.PublicationRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements IPublicationService{

    private final PublicationRepositoryImpl publicationRepository;
    private final IUserService userService;

    public PublicationServiceImpl(PublicationRepositoryImpl publicationRepository, IUserService userService) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
    }


    @Override
    public ResponseEntity<?> newPost(Integer userId, PublicationRequestDTO publicationRequestDTO) throws UserNotFindOrEqualException {
        UserInfo user = userService.getUserFromRepository(userId);
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
            throw new UserNotFindOrEqualException();
        }
    }

    @Override
    public FollowerPostListDTO getAllPost(Integer userId, String mode) throws UserNotFindOrEqualException {
        FollowerPostListDTO response = new FollowerPostListDTO();
        UserInfo user = userService.getUserFromRepository(userId);
        if (user != null ){
            List<Integer> allFollowersId = user.getFollower().stream().map(UserDTO::getUserId).collect(Collectors.toList());
            List<Publication> allFollowersPost = new ArrayList<>();
            allFollowersId.forEach(id-> { allFollowersPost.addAll(publicationRepository.getPosts(id)); });
            response.setPost(orderPublicationList(allFollowersPost, mode));
            response.setUserId(userId);
            return response;
        }else{
            throw new UserNotFindOrEqualException();
        }
    }

    private List<Publication> orderPublicationList(List<Publication> list, String mode){
        List<Publication> aux = new ArrayList<>(list);
        LocalDate dateLimit = LocalDate.now().minusDays(14);
        //dateLimit is date two week before of today
        //filter date after to dateLimit
        aux = aux.stream().filter(post -> post.getDate().isAfter(dateLimit)).collect(Collectors.toList());
        Comparator<Publication> mapComparator = null;
        if(mode.compareTo("date_asc") == 0){
            mapComparator = (Publication m1, Publication m2) -> m1.getDate().compareTo(m2.getDate());
        }
        if(mode.compareTo("date_desc") == 0){
            mapComparator = (Publication m1, Publication m2) -> m2.getDate().compareTo(m1.getDate());
        }
        Collections.sort(aux, mapComparator);
        return aux;
    }

}
