package com.meli.joescaos.socialmeli.socialmeli.services.Impl;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.BuyerFollowingsDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerDto;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserAlreadyFollowedException;
import com.meli.joescaos.socialmeli.socialmeli.models.Buyer;
import com.meli.joescaos.socialmeli.socialmeli.models.Post;
import com.meli.joescaos.socialmeli.socialmeli.models.Seller;
import com.meli.joescaos.socialmeli.socialmeli.repositories.BuyerRepository;
import com.meli.joescaos.socialmeli.socialmeli.repositories.PostRepository;
import com.meli.joescaos.socialmeli.socialmeli.repositories.SellerRepository;
import com.meli.joescaos.socialmeli.socialmeli.services.BuyerService;
import com.meli.joescaos.socialmeli.socialmeli.utils.OrderUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyerServiceImpl implements BuyerService {

    // Attributes
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final PostRepository postRepository;

    // Constructor
    public BuyerServiceImpl(BuyerRepository buyerRepository, SellerRepository sellerRepository,
                            PostRepository postRepository) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.postRepository = postRepository;
    }

    /**
     *
     * @param buyerId
     * @param sellerId
     */
    @Override
    public void followSeller(int buyerId, int sellerId) {
        Buyer buyer = buyerRepository.findById(buyerId);
        Seller seller = sellerRepository.findById(sellerId);
        if (buyer.getFollowing().contains(seller) || seller.getFollowers().contains(buyer)){
            new UserAlreadyFollowedException();
        }
        else {
            buyer.setUserId(buyer.getUserId());
            buyer.setUserName(buyer.getUserName());
            seller.setUserId(seller.getUserId());
            seller.setUserName(seller.getUserName());

            buyer.followSeller(seller);
            seller.followedBy(buyer);
        }
    }

    /**
     *
     * @param buyerId
     * @param sellerId
     */
    @Override
    public void unfollowSeller(int buyerId, int sellerId) {
        Buyer buyer = buyerRepository.findById(buyerId);
        Seller seller = sellerRepository.findById(sellerId);
        buyer.unfollowSeller(seller);
    }

    /**
     *
     * @param userId
     * @param order
     * @return BuyerFollowingsDto
     */
    @Override
    public BuyerFollowingsDto getUserFollowedList(int userId, String order) {
        Buyer buyer = buyerRepository.findById(userId);
        List<Seller> sellers = buyer.getFollowing();
        List<SellerDto> sellersDto = new ArrayList<>();
        BuyerFollowingsDto buyerFollowingslist = new BuyerFollowingsDto();
        buyerFollowingslist.setUserId(buyer.getUserId());
        buyerFollowingslist.setUserName(buyer.getUserName());
        sellers.forEach(seller -> {
            SellerDto sellerDto = new SellerDto();
            sellerDto.setUserId(seller.getUserId());
            sellerDto.setUserName(seller.getUserName());
            sellersDto.add(sellerDto);
        });
        buyerFollowingslist.setFollowed(sellersDto);

        if (order != null)
          OrderUtils.orderFollowed(buyerFollowingslist.getFollowed(), order);

        return buyerFollowingslist;
    }

    /**
     *
     * @param userId
     * @param order
     * @return PostListDto
     */
    @Override
    public PostsListDto getPostsList(int userId, String order) {
        PostsListDto postsListDto = new PostsListDto();
        Buyer buyer = buyerRepository.findById(userId);
        postsListDto.setUserId(buyer.getUserId());
        List<Seller> sellers = buyer.getFollowing();
        List<Post> posts = postRepository.getPosts();
        for (Post post : posts) {
            for (Seller seller : sellers) {
                if (post.getUserId() == seller.getUserId()) {
                    postsListDto.addPost(post);
                }
            }
        }
        List<Post> filteredposts = postsListDto.getPosts().stream().filter(post ->
                getLastTwoWeeks(DateToLocalDate(post.getDate())))
                .collect(Collectors.toList());
        postsListDto.setPosts(filteredposts);
        if (order != null) {
            OrderUtils.orderDates(postsListDto.getPosts(), order);
        }
        return postsListDto;
    }

    /**
     *
     * @param date
     * @return LocalDate
     */
    private LocalDate DateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     *
     * @param date
     * @return date if is in the las two weeks
     */
    private boolean getLastTwoWeeks(LocalDate date){
        return date.isAfter(LocalDate.now().minusWeeks(2));
    }
}
