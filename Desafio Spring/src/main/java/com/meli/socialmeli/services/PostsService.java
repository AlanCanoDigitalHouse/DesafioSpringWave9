package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.request.NewpostDTO;
import com.meli.socialmeli.dtos.response.FollowedUserListDTO;
import com.meli.socialmeli.dtos.response.PostDTO;
import com.meli.socialmeli.repositories.PostsRepository;
import com.meli.socialmeli.utils.SortPostDTOByDate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PostsService implements IPostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository){ this.postsRepository = postsRepository; }

    public List<PostDTO> getFollowedPostings(FollowedUserListDTO followed, String order){
        if(order != null && order.equals("date_asc")){
            return orderDateAsc(postsRepository.usersPosts(followed.getFollowed()));
        }
        return orderDateDesc(postsRepository.usersPosts(followed.getFollowed()));
    }

    public void posting(NewpostDTO p){
        postsRepository.addPost(p);
    }

    @Override
    public List<PostDTO> orderDateAsc(List<PostDTO> posts) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.WEEK_OF_YEAR, -2);
        posts.sort(new SortPostDTOByDate().reversed());
        return posts;
    }

    @Override
    public List<PostDTO> orderDateDesc(List<PostDTO> posts) {
        posts.sort(new SortPostDTOByDate());
        return posts;
    }
}
