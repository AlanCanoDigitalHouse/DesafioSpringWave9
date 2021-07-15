package com.example.demo.services;

import com.example.demo.dtos.request.Post;
import com.example.demo.dtos.response.PostList;
import com.example.demo.exceptions.EmptyFile;
import com.example.demo.exceptions.InvalidDate;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.respositories.PostImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    //Global Variables
    private static final String PATH = System.getProperty("user.dir") + "/src/main/resources/static/posts.json";
    private static final LocalDate TODAY = LocalDate.now();
    private static final DateTimeFormatter DATEFF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //Repository
    private PostImpl postImpl;

    public PostService(PostImpl postImpl) {
        this.postImpl = postImpl;
    }

    //Create and save a new post
    public void createPost(Post post) throws FileNotFoundException, InvalidDate {

        List<Post> posts = postImpl.loadDatabase();
        post.setId_post(posts.size() + 1);
        post.getDetail().setProduct_id(posts.size() + 1);
        parseDate(post.getDate());
        posts.add(post);
        addPost(posts);
    }

    private void parseDate(LocalDate date) {
        date.toString();
    }

    //Get all the post by user
    public PostList getPostList(Integer id) throws UserNotFound, EmptyFile {
        List<Post> posts = postImpl.loadDatabase();
        List<Post> postByUser = filterPostByUser(posts, id);
        PostList postList = new PostList();
        postList.setUserId(id);
        postList.setPosts(postByUser);
        return postList;
    }

    //Get post list by specific order
    public PostList orderPost(String type, Integer id) throws UserNotFound {
        List<Post> posts = postImpl.loadDatabase();
        List<Post> postList = new ArrayList<>();
        PostList postListDto = new PostList();
        if(Objects.nonNull(posts)){
            postList = posts.stream().filter(post -> post.getUserId().equals(id)).collect(Collectors.toList());
        } else {
            throw new UserNotFound();
        }
        if(type.equals("date_asc")) {
            order(postList);
            postListDto.setUserId(id);
            postListDto.setPosts(postList);
        } else if (type.equals("date_desc")) {
            order(postList);
            Collections.reverse(postList);
            postListDto.setUserId(id);
            postListDto.setPosts(postList);
        }
        return postListDto;
    }

    private void order(List<Post> posts) {
       Collections.sort(posts, new SortByDate());
    }

    private void addPost(List<Post> postList) throws FileNotFoundException {
        try {
            var file = ResourceUtils.getFile(PATH);
            var mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            mapper.writeValue(file, new ArrayList<>(postList));
        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Post> filterPostByUser(List<Post> posts, Integer id) throws UserNotFound, EmptyFile {
        if(Objects.nonNull(posts)){
            List<Post> validPost = posts.stream().filter(post -> !post.getUserId().equals("")).collect(Collectors.toList());
            List<Post> postList = validPost.stream().filter(post -> post.getUserId().equals(id)).collect(Collectors.toList());
            if(postList.isEmpty())
                throw new UserNotFound();
            else
                return filterPostByDate(postList);
        } else {
            throw new EmptyFile();
        }
    }

    private List<Post> filterPostByDate(List<Post> posts) throws EmptyFile {
        if(Objects.nonNull(posts)){
            //List<Post> validPost = posts.stream().filter(post -> !post.getDate().isBlank() || !post.getDate().isEmpty()).collect(Collectors.toList());
            /*List<Post> postList = validPost.stream().filter(post ->
                    LocalDate.parse(post.getDate(), DATEFF).plusWeeks(2).isAfter(TODAY) ||
                            LocalDate.parse(post.getDate(), DATEFF).plusWeeks(2).isEqual(TODAY)).collect(Collectors.toList());*/
            List<Post> postList = posts.stream().filter(post ->
                    post.getDate().plusWeeks(2).isAfter(TODAY) ||
                            post.getDate().plusWeeks(2).isEqual(TODAY)).collect(Collectors.toList());
            order(postList);
            Collections.reverse(postList);
            return postList;
        } else {
            throw new EmptyFile();
        }
    }
}
