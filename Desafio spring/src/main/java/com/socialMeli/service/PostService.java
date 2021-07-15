package com.socialMeli.service;

import com.socialMeli.dto.request.product.DetailProductDTO;
import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.dto.response.*;
import com.socialMeli.exception.exception.DateNotValidException;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.exception.exception.OrderNotValidException;
import com.socialMeli.model.PostModel;
import com.socialMeli.model.ProductModel;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.PostRepository;
import com.socialMeli.repository.UserRepository;
import com.socialMeli.utils.DateValidatorDateTimeFormatter;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addNewPost(@Valid PostInfoToCreateDTO dataPost) throws ParseException, ModelNotExists, ModelAlreadyExists, DateNotValidException {
        PostModel postModel = parseToPostModel(dataPost);
        postRepository.insert(postModel);
    }

    @Override
    public ProductsSellersThatUserFollowsDTO postSellersThatUserFollows(int userId, String order) throws ModelNotExists, OrderNotValidException {
        UserModel user = userRepository.findById(userId);
        List<UserModel> usersFollowed = getListUserById(user.getFollowed());
        List<PostModel> posts = getPostsOfUsersBeforeADate(usersFollowed, restTwoWeekToDate(new Date()));
        List<PostInfoResponseDTO> postInfoResponseDTOS = posts.stream()
                .filter(post -> post.getHasPromo() == null || !post.getHasPromo())
                .map(postModel -> {
                    //Create detail
                    ProductDetailResponseDTO info = new ProductDetailResponseDTO(
                            postModel.getDetail().getProduct_id(),
                            postModel.getDetail().getProductName(),
                            postModel.getDetail().getType(),
                            postModel.getDetail().getBrand(),
                            postModel.getDetail().getColor(),
                            postModel.getDetail().getNotes()
                    );
                    //Create PostInfo
                    return new PostInfoResponseDTO(
                            postModel.getId(),
                            postModel.getDate(),
                            info,
                            String.valueOf(postModel.getCategory()),
                            postModel.getPrice()
                    );
                }).collect(Collectors.toList());
        orderBy(postInfoResponseDTOS, order);
        return new ProductsSellersThatUserFollowsDTO(user.getId(), postInfoResponseDTOS);
    }

    private void orderBy(List<PostInfoResponseDTO> listToOrder, String order) throws OrderNotValidException {
        switch (order) {
            case "date_asc":
                listToOrder.sort((Comparator.comparing(PostInfoResponseDTO::getDate)));
                break;
            case "date_desc":
                listToOrder.sort(((o1, o2) -> o2.getDate().compareTo(o1.getDate())));
                break;
            default:
                throw new OrderNotValidException(order, "date_asc, date_desc");
        }
    }

    private Date createDate(String date) throws DateNotValidException, ParseException {
        Date today = new Date();
        if (!DateValidatorDateTimeFormatter.isValid(date)) throw new DateNotValidException(date);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if (today.compareTo(format.parse(date)) < 0)
            throw new DateNotValidException(date, "That date is in the  future!");
        return format.parse(date);
    }

    private List<UserModel> getListUserById(List<Integer> ids) throws ModelNotExists {
        List<UserModel> users = new ArrayList<>();
        for (Integer id : ids) users.add(userRepository.findById(id));
        return users;
    }

    private List<PostModel> getPostsOfUsersBeforeADate(List<UserModel> users, Date limitDateIncluded) {
        List<PostModel> posts = new ArrayList<>();
        for (UserModel user : users) {
            posts.addAll(getPostsOfUserBeforeADate(user, limitDateIncluded));
        }
        posts.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return posts;
    }


    private List<PostModel> getPostsOfUserBeforeADate(UserModel user, Date limitDateIncluded) {
        List<PostModel> all = postRepository.findAll();
        return all.stream().filter(post -> post.getUserId() == user.getId() && dateIsInLimit(limitDateIncluded, post.getDate())).collect(Collectors.toList());
    }

    private List<PostModel> getPostsOfUser(UserModel user) {
        List<PostModel> all = postRepository.findAll();
        return all.stream().filter(post -> post.getUserId() == user.getId()).collect(Collectors.toList());
    }

    private boolean dateIsInLimit(Date limit, Date eval) {
        Date today = new Date();
        return eval.before(today) && eval.after(limit);
    }

    private Date restTwoWeekToDate(Date date) {
        //Parsing to localDate, more easy https://stackoverflow.com/questions/11882926/how-to-subtract-x-day-from-a-date-object-in-java
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        ldt = ldt.minusDays(7 * 2);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    private ProductModel parseToProductModel(DetailProductDTO dto, int productId) {
        return ProductModel.builder()
                .product_id(productId)
                .productName(dto.getProductName())
                .type(dto.getType())
                .brand(dto.getBrand())
                .color(dto.getColor())
                .notes(dto.getNotes())
                .build();
    }

    private <T extends PostInfoToCreateDTO> PostModel parseToPostModel(T dataPost) throws ModelNotExists, DateNotValidException, ParseException {
        UserModel userModel = userRepository.findById(dataPost.getUserId());
        //Default Data
        PostModel.PostModelBuilder postModelBuilder = PostModel.builder()
                .id(postRepository.getNextId())
                .category(dataPost.getCategory())
                .date(createDate(dataPost.getDate()))
                .price(dataPost.getPrice())
                .userId(userModel.getId())
                .detail(parseToProductModel(dataPost.getDetail(), postRepository.getNextId()));
        //If more specific class has been send
        if ("PromoPostInfoToCreateDTO".equals(dataPost.getClass().getSimpleName())) {
            try {
                Method getDiscount = dataPost.getClass().getMethod("getDiscount");
                Method getHasPromo = dataPost.getClass().getMethod("getHasPromo");
                postModelBuilder.hasPromo((Boolean) getHasPromo.invoke(dataPost));
                postModelBuilder.discount((Double) getDiscount.invoke(dataPost));
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ex) {
                ex.printStackTrace();
                System.out.println("Error fatal :( " + ex.getMessage());
            }
        }
        return postModelBuilder.build();
    }

    @Override
    public CountPromoPostsResponseDTO countPromoPosts(int userId) throws ModelNotExists {
        UserModel user = userRepository.findById(userId);
        List<PostModel> promoPosts = getPostsOfUser(user).stream().filter(post -> {
            try {
                return post.getHasPromo();
            } catch (NullPointerException ex) {
                //Empty, is normal that promo was null
            }
            return false;
        }).collect(Collectors.toList());
        return CountPromoPostsResponseDTO.builder()
                .promoproducts_count(promoPosts.size())
                .userName(user.getUserName())
                .userId(String.valueOf(user.getId()))
                .build();
    }

    @Override
    public PromoPostsOfAUserResponseDTO getPromoPostsLists(int userId) throws ModelNotExists {
        UserModel userModel = userRepository.findById(userId);
        List<PostModel> promoPosts = getPromoPostsOfUser(userModel);
        List<PromoPostInfoResponseDTO> promoPostDTO = parseModelToDTO(promoPosts);
        return new PromoPostsOfAUserResponseDTO(userModel.getId(), userModel.getUserName(), promoPostDTO);
    }

    private List<PostModel> getPromoPostsOfUser(UserModel user) {
        return getPostsOfUser(user).stream().filter(post -> {
            try {
                return post.getHasPromo();
            } catch (NullPointerException ex) {
                //Empty, is normal that promo was null
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<PromoPostInfoResponseDTO> parseModelToDTO(List<PostModel> promoPosts) {
        return promoPosts.stream()
                //Parse model to dto
                .map(promoModel -> {
                    PromoPostInfoResponseDTO out = new PromoPostInfoResponseDTO();
                    out.setId_post(promoModel.getId());
                    out.setDate(promoModel.getDate());
                    out.setDetail(ProductDetailResponseDTO.builder()
                            .product_id(promoModel.getDetail().getProduct_id())
                            .productName(promoModel.getDetail().getProductName())
                            .type(promoModel.getDetail().getType())
                            .brand(promoModel.getDetail().getBrand())
                            .color(promoModel.getDetail().getColor())
                            .notes(promoModel.getDetail().getNotes())
                            .build()
                    );
                    out.setCategory(String.valueOf(promoModel.getCategory()));
                    out.setPrice(promoModel.getPrice());
                    out.setHasPromo(promoModel.getHasPromo());
                    out.setDiscount(promoModel.getDiscount());
                    return out;
                }).collect(Collectors.toList());
    }


}
