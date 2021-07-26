package com.apismeli.socialmeli.services;

import com.apismeli.socialmeli.dtos.request.PublicationDTO;
import com.apismeli.socialmeli.dtos.request.MarketDTO;
import com.apismeli.socialmeli.dtos.request.UserDTO;
import com.apismeli.socialmeli.dtos.request.SellerDTO;
import com.apismeli.socialmeli.dtos.response.PostResponseDTO;
import com.apismeli.socialmeli.dtos.response.PostBySellerDTO;
import com.apismeli.socialmeli.repositories.InitializeDataRepositoryImpl;
import com.apismeli.socialmeli.utilities.comparePostDateAsc;
import com.apismeli.socialmeli.utilities.comparePostDateDesc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PostService implements IPostService {
    private ArrayList<Integer> postList = new ArrayList<Integer>();
    private MarketDTO marketDTO;
    private PostBySellerDTO postBySellerDTO;

    UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity register(PublicationDTO publicationDTO) throws Exception {
        try {
            if (!postList.contains(publicationDTO.getId_post())) {
                if (postBySellerDTO.getSellerPosts().containsKey(publicationDTO.getUserId())) {
                    postList.add(publicationDTO.getId_post());
                    postBySellerDTO.getSellerPosts().get(publicationDTO.getUserId()).add(new PostResponseDTO(publicationDTO));
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public PostBySellerDTO initializer() {
        marketDTO = InitializeDataRepositoryImpl.marketDTO;
        postBySellerDTO = new PostBySellerDTO();
        for (SellerDTO seller : marketDTO.getSellers()) {
            postBySellerDTO.getSellerPosts().put(seller.getUserId(), new ArrayList<>());
        }
        return postBySellerDTO;
    }

    @Override
    public Object showPosts(Integer userId, String order) throws Exception {
        try {

            if (Objects.isNull(order)) {
                ArrayList<PostResponseDTO> postsLess15 = less15Days(userId);
                sortDateDesc(postsLess15);
                return postsLess15;
            } else if (order.equals("date_asc")) {
                ArrayList<PostResponseDTO> posts = allPost(userId);
                sortDateAsc(posts);
                return posts;
            } else if (order.equals("date_desc")) {
                ArrayList<PostResponseDTO> posts = allPost(userId);
                sortDateDesc(posts);
                return posts;
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private ArrayList<PostResponseDTO> less15Days(Integer userId) throws Exception {
        try {
            LocalDate fifteenDaysAgo = LocalDate.now();
            fifteenDaysAgo = fifteenDaysAgo.minusDays(15);
            List<UserDTO> followedList = userService.whoIFollow(userId).getFollowed();
            ArrayList<PostResponseDTO> posts = new ArrayList<PostResponseDTO>();
            for (UserDTO following : followedList) {
                for (PostResponseDTO post : postBySellerDTO.getSellerPosts().get(following.getUserId())) {
                    if (post.getDate().isBefore(fifteenDaysAgo)) {
                        posts.add(post);
                    }
                }

            }
            return posts;
        } catch (Exception e) {
            throw e;
        }
    }

    private ArrayList<PostResponseDTO> allPost(Integer userId) throws Exception {
        try {
            List<UserDTO> followedList = userService.whoIFollow(userId).getFollowed();
            ArrayList<PostResponseDTO> posts = new ArrayList<PostResponseDTO>();
            for (UserDTO following : followedList) {
                posts.addAll(postBySellerDTO.getSellerPosts().get(following.getUserId()));
            }
            return posts;
        } catch (Exception e) {
            throw e;
        }
    }


    private void sortDateAsc(ArrayList<PostResponseDTO> postList) {
        Collections.sort(postList, new comparePostDateAsc());
    }

    private void sortDateDesc(ArrayList<PostResponseDTO> postList) {
        Collections.sort(postList, new comparePostDateDesc());
    }
}
