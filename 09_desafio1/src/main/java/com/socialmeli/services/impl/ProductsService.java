package com.socialmeli.services.impl;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.request.PostRequestDTO;
import com.socialmeli.dtos.request.SortEnum;
import com.socialmeli.dtos.response.PostResponseDTO;
import com.socialmeli.dtos.response.ResponseObjectDTO;
import com.socialmeli.helpers.PostHelper;
import com.socialmeli.models.PostSocial;
import com.socialmeli.models.User;
import com.socialmeli.models.UserSocial;
import com.socialmeli.repositories.IPostRepository;
import com.socialmeli.repositories.ISocialRepository;
import com.socialmeli.services.IProductsService;
import com.socialmeli.utils.Comparators.ComparatorPostDate;
import com.socialmeli.utils.Comparators.IComparator;
import com.socialmeli.utils.SortUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductsService implements IProductsService {

    private final IPostRepository postRepository;
    private final ISocialRepository socialRepository;


    public ProductsService(IPostRepository postRepository, ISocialRepository socialRepository) {
        this.postRepository = postRepository;
        this.socialRepository = socialRepository;
    }

    @Override
    public ResponseDTO addPostSocial(PostRequestDTO post) {
        socialRepository.findUserbyId(post.getUserId());

        PostSocial newPost = PostHelper.mapper(post);
        postRepository.savePost(newPost);

        return new ResponseDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Posts save successfully"
        );
    }

    @Override
    public ResponseDTO listPostFollowed(Integer idUser, SortEnum sort) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        ArrayList<Integer> idUsers = user.getFollowed().stream().map(User::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        // Filtered by last days and with users followed
        ArrayList<PostSocial> filterDatePosts = filterWithFollowedPost(
                postRepository.filterByDate(15),
                idUsers
        );
        //Sort post by date
        ArrayList<PostResponseDTO> response = new ArrayList<>();
        filterDatePosts.forEach((PostSocial post) ->
            response.add(PostHelper.mapper(post))
        );


        orderPostByDate(response, sort);

        return new ResponseObjectDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Posts list filtered successfully",
                response
        );
    }

    @Override
    public ResponseDTO listPosts() {
        ArrayList<PostSocial> response = postRepository.list();
        return new ResponseObjectDTO(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Posts list successfully extracted",
                response);
    }

    private ArrayList<PostSocial> filterWithFollowedPost(ArrayList<PostSocial> posts, ArrayList<Integer> followedIds) {
        return posts.stream().filter(post ->
                followedIds.contains(post.getUserId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void orderPostByDate(ArrayList<PostResponseDTO> post, SortEnum sort) {
        IComparator<PostResponseDTO> comparator = new ComparatorPostDate();
        if (sort.equals(SortEnum.date_asc))
            SortUtils.sortAsc(post, comparator);
        else if (sort.equals(SortEnum.date_desc))
            SortUtils.sortDesc(post, comparator);
    }
}
