package com.socialmeli.services;

import com.socialmeli.dtos.PostDTO;
import com.socialmeli.dtos.ProductDTO;
import com.socialmeli.dtos.PromoPostDTO;
import com.socialmeli.dtos.UserDTO;
import com.socialmeli.dtos.requests.PostRequestDTO;
import com.socialmeli.dtos.requests.ProductRequestDTO;
import com.socialmeli.dtos.requests.PromoPostRequestDTO;
import com.socialmeli.dtos.responses.*;
import com.socialmeli.exceptions.ApiException;
import com.socialmeli.repositories.SocialMeliRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceImp implements SocialMeliService {

    private static final String NAME_ASC = "name_asc", NAME_DESC = "name_desc",
            DATE_ASC = "date_asc", DATE_DESC = "date_desc";

    SocialMeliRepository socialMeliRepository;

    public SocialMeliServiceImp(SocialMeliRepository socialMeliRepository){
        this.socialMeliRepository = socialMeliRepository;
    }

    @Override
    public void follow(Integer userId, Integer userToFollowId) {
        UserDTO user = findUser(userId);
        UserDTO userToFollow = findUser(userToFollowId);

        follow(user, userToFollow);
    }

    @Override
    public CountFollowersResponseDTO countFollowers(Integer userId) {
        UserDTO user = findUser(userId);
        return new CountFollowersResponseDTO(user.getUserId(), user.getUserName(), user.getFollowers().size());
    }

    @Override
    public FollowersResponseDTO getFollowers(Integer userId) {
        UserDTO user = findUser(userId);
        return new FollowersResponseDTO(user.getUserId(), user.getUserName(), user.getFollowers());
    }

    @Override
    public FollowedResponseDTO getFollowed(Integer userId) {
        UserDTO user = findUser(userId);
        return new FollowedResponseDTO(user.getUserId(), user.getUserName(), user.getFollowed());
    }

    @Override
    public FollowersResponseDTO getFollowers(Integer userId, String order) {
        UserDTO user = findUser(userId);
        return new FollowersResponseDTO(user.getUserId(), user.getUserName(),
                orderUser(user.getFollowers(), order));
    }

    @Override
    public FollowedResponseDTO getFollowed(Integer userId, String order) {
        UserDTO user = findUser(userId);
        return new FollowedResponseDTO(user.getUserId(), user.getUserName(),
                orderUser(user.getFollowed(), order));
    }

    @Override
    public void createPost(PostRequestDTO request) {
        UserDTO user = findUser(request.getUserId());
        ProductRequestDTO detail = request.getDetail();
        ProductDTO product = new ProductDTO(socialMeliRepository.lastProductId(),
                detail.getProductName(), detail.getType(),
                detail.getBrand(), detail.getColor(),
                detail.getNotes());
        PostDTO post = new PostDTO(request.getUserId(), socialMeliRepository.lastPostId(),
                getDate(request.getDate()), product, request.getCategory(), request.getPrice());
        user.getPosts().add(post);
        socialMeliRepository.save(user);
    }

    @Override
    public LastProductsResponseDTO getLastProducts(Integer userId) {
        UserDTO user = findUser(userId);
        Date twoWeekBefore = getTwoWeekBeforeDate();
        List<PostDTO> posts = user.getFollowed().stream().flatMap(u -> u.getPosts().stream().filter(post -> post.getDate().after(twoWeekBefore))).collect(Collectors.toList());

        return new LastProductsResponseDTO(user.getUserId(), posts.stream()
                .map(this::mapPostResponse).collect(Collectors.toList()));
    }


    @Override
    public LastProductsResponseDTO getLastProducts(Integer userId, String order) {
        UserDTO user = findUser(userId);
        Date twoWeekBefore = getTwoWeekBeforeDate();
        List<PostDTO> posts = user.getFollowed().stream().flatMap(u -> u.getPosts().stream().filter(post -> post.getDate().after(twoWeekBefore))).collect(Collectors.toList());

        return new LastProductsResponseDTO(user.getUserId(), orderPost(posts, order).stream()
                .map(this::mapPostResponse).collect(Collectors.toList()));
    }

    @Override
    public void unfollow(Integer userId, Integer userToUnfollowId) {
        UserDTO user = findUser(userId);
        UserDTO userToUnfollow = findUser(userToUnfollowId);

        unfollow(user, userToUnfollow);
    }

    @Override
    public void createPostPromo(PromoPostRequestDTO request) {
        UserDTO user = findUser(request.getUserId());
        ProductRequestDTO detail = request.getDetail();
        ProductDTO product = new ProductDTO(socialMeliRepository.lastProductId(),
                detail.getProductName(), detail.getType(),
                detail.getBrand(), detail.getColor(),
                detail.getNotes());
        PromoPostDTO post = new PromoPostDTO(request.getUserId(), socialMeliRepository.lastPostId(),
                getDate(request.getDate()), product, request.getCategory(), request.getPrice(),
                request.getHasPromo(), request.getDiscount());
        user.getPosts().add(post);
        socialMeliRepository.save(user);
    }

    @Override
    public CountPromoResponseDTO countPromo(Integer userId) {
        UserDTO user = findUser(userId);
        return new CountPromoResponseDTO(user.getUserId(), user.getUserName(), (int) user.getPosts().stream()
                .filter(p -> p instanceof PromoPostDTO && (((PromoPostDTO) p).getHasPromo())).count());
    }

    @Override
    public ListPromoResponseDTO listPromo(Integer userId) {
        UserDTO user = findUser(userId);
        return new ListPromoResponseDTO(user.getUserId(), user.getUserName(), user.getPosts()
                .stream().filter(p -> p instanceof PromoPostDTO && (((PromoPostDTO) p).getHasPromo()))
                .map(p -> this.mapPromoPostResponse((PromoPostDTO) p)).collect(Collectors.toList()));
    }

    //obtiene usuario del repositorio en caso de no existir devuelve un not found.
    private UserDTO findUser(Integer userId) {
        Optional<UserDTO> userDTO = socialMeliRepository.findUserByUserId(userId);

        if(userDTO.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("userId", "Not Found user with id: " + userId.toString());
            throw new ApiException(HttpStatus.NOT_FOUND.value(), "Not Found", errors);
        }

        return userDTO.get();
    }

    private void follow(UserDTO user, UserDTO userToFollow) {

        if(user.getFollowed().contains(userToFollow) && userToFollow.getFollowers().contains(user)){
            return;
        }

        user.getFollowed().add(userToFollow);
        userToFollow.getFollowers().add(user);
        socialMeliRepository.save(user);
        socialMeliRepository.save(userToFollow);
    }

    private void unfollow(UserDTO user, UserDTO userToUnfollow) {
        if(!user.getFollowed().contains(userToUnfollow) && !userToUnfollow.getFollowers().contains(user)){
            return;
        }

        user.getFollowed().remove(userToUnfollow);
        userToUnfollow.getFollowers().remove(user);
        socialMeliRepository.save(user);
        socialMeliRepository.save(userToUnfollow);
    }

    //recibe una lista y la ordena según el valor de order.
    private List<PostDTO> orderPost(List<PostDTO> posts, String order) {
        List<PostDTO> response = posts;
        switch(order) {
            case DATE_ASC:
                response = posts.stream()
                        .sorted(Comparator.comparing(PostDTO::getDate)).collect(Collectors.toList());
                break;
            case DATE_DESC:
                response = posts.stream()
                        .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate())).collect(Collectors.toList());
                break;
        }
        return response;
    }

    //recibe una lista y la ordena según el valor de order.
    private List<UserDTO> orderUser(List<UserDTO> users, String order) {
        List<UserDTO> response = users;
        switch(order) {
            case NAME_ASC:
                response = users.stream()
                        .sorted(Comparator.comparing(UserDTO::getUserName)).collect(Collectors.toList());
                break;
            case NAME_DESC:
                response = users.stream()
                        .sorted(Comparator.comparing(UserDTO::getUserName).reversed()).collect(Collectors.toList());
                break;
        }
        return response;
    }

    //devuelve un objeto Date que representa la fecha de dos semanas atras.
    private Date getTwoWeekBeforeDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -14);
        return calendar.getTime();
    }

    //transforma un objeto String en un objeto Date de no lograr hacer la conversión retorna un error 500.
    private Date getDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("date", "date parse error");
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", errors);
        }
    }

    private PostResponseDTO mapPostResponse(PostDTO post) {
        return new PostResponseDTO(post.getPostId(), new SimpleDateFormat("dd-MM-yyyy").format(post.getDate()),
                new ProductResponseDTO(post.getDetail()), post.getCategory(), post.getPrice());
    }

    private PromoPostResponseDTO mapPromoPostResponse(PromoPostDTO post) {
        return new PromoPostResponseDTO(post.getPostId(), new SimpleDateFormat("dd-MM-yyyy").format(post.getDate()),
                new ProductResponseDTO(post.getDetail()), post.getCategory(), post.getPrice(),
                post.getHasPromo(), post.getDiscount());
    }

}
