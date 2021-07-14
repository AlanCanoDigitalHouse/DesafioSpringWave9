package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.Publication;
import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowerPostListDTO;
import com.example.desafiospring.dtos.response.UserDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.repositories.PublicationRepositoryImpl;
import com.example.desafiospring.repositories.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public FollowerPostListDTO getAllPost(Integer userId, String mode) throws UserNotFindException {
        FollowerPostListDTO response = new FollowerPostListDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null ){
            List<Integer> allFollowersId = user.getFollower().stream().map(UserDTO::getUserId).collect(Collectors.toList());
            System.out.println("followers ids: "+ allFollowersId);
            List<Publication> allFollowersPost = new ArrayList<>();
            allFollowersId.forEach(id-> { allFollowersPost.addAll(publicationRepository.getPosts(id)); });
            response.setPost(orderPublicationList(allFollowersPost, mode));
            response.setUserId(userId);
            return response;
        }else{
            throw new UserNotFindException();
        }
    }

    private List<Publication> orderPublicationList(List<Publication> list, String mode){
        List<Publication> aux = new ArrayList<>(list);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateLimit = LocalDate.now().minusDays(14);
        //dateLimit is date two week before of today
        //filter date after to dateLimit
        aux = list.stream().filter(post -> post.getDate().isAfter(dateLimit)).collect(Collectors.toList());
        Comparator<Publication> mapComparator = null;
        if(mode.compareTo("date_asc") == 0){
            System.out.println("ordenando por fecha asc");
            mapComparator = (Publication m1, Publication m2) -> m1.getDate().compareTo(m2.getDate());
        }
        if(mode.compareTo("date_desc") == 0){
            System.out.println("ordenando por fecha desc");
            mapComparator = (Publication m1, Publication m2) -> m2.getDate().compareTo(m1.getDate());
        }
        Collections.sort(aux, mapComparator);
        return aux;
    }

}
