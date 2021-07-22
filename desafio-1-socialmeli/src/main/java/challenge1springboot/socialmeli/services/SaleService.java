package challenge1springboot.socialmeli.services;

import challenge1springboot.socialmeli.DTO.SaleDTO;
import challenge1springboot.socialmeli.DTO.request.NewPostRequestDTO;
import challenge1springboot.socialmeli.DTO.request.NewSaleRequestDTO;
import challenge1springboot.socialmeli.DTO.response.SaleListResponseDTO;
import challenge1springboot.socialmeli.DTO.response.SaleQuantityResponseDTO;
import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.Sale;
import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.exceptions.post.InvalidPostException;
import challenge1springboot.socialmeli.exceptions.sale.InvalidSaleException;
import challenge1springboot.socialmeli.exceptions.user.InvalidUserException;
import challenge1springboot.socialmeli.globalconstants.Message;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.repositories.PostRepository;
import challenge1springboot.socialmeli.repositories.SaleRepository;
import challenge1springboot.socialmeli.repositories.UserRepository;
import challenge1springboot.socialmeli.utils.SorterUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Service
public class SaleService {

    private final PostService postService = new PostService();
    private final SaleRepository saleRepository = new SaleRepository();
    private final UserRepository userRepository = new UserRepository();
    private final PostRepository postRepository = new PostRepository();

    // Service SALE -> POST -> SALE {Complete cycle pattern}
    public Sale newPostSale(NewSaleRequestDTO newSaleRequestDTO) {
        Post post = postService.newPost(new NewPostRequestDTO(
                newSaleRequestDTO.getUserId(),
                newSaleRequestDTO.getDate(),
                newSaleRequestDTO.getDetail(),
                newSaleRequestDTO.getCategory(),
                newSaleRequestDTO.getPrice()));
        if (Objects.isNull(post)) throw new InvalidPostException(Message.SALE_NOT_PROCESSED_CAUSE_POST);
        try {
            return saleRepository.save(
                    newSaleRequestDTO.getUserId(),
                    post.getId_post(),
                    post.getDetail().getProduct_id(),
                    newSaleRequestDTO.isHasPromo(),
                    newSaleRequestDTO.getDiscount());
        } catch (Exception invalidSale) {
            throw new InvalidPostException(Message.SALE_NOT_PROCESSED);
        }
    }

    public SaleQuantityResponseDTO countSale(String userId) {
        User user = userGetter(userId);
        if (Objects.isNull(user)) throw new InvalidUserException(Message.INVALID_USER_APPROACH);
        try {
            int sales = saleRepository.getSaleByUser(user.getUserId());
            return new SaleQuantityResponseDTO(user.getUserId(), user.getUserName(), sales);
        } catch (Exception e) {
            throw new InvalidSaleException(Message.SALE_NOT_PROCESSED);
        }
    }

    public SaleListResponseDTO listSales(String userId, String order) {
        User user = userGetter(userId);
        if (Objects.isNull(user))
            throw new InvalidUserException(Message.INVALID_USER_APPROACH);
        List<Sale> salesList = saleRepository.listSalesPublishedByUser(user.getUserId());
        SaleListResponseDTO response = new SaleListResponseDTO(user.getUserId(), user.getUserName(), new ArrayList<>());
        if (salesList.size() > 0) {
            List<Post> postsList = postRepository.listPostPublishedByUser(user.getUserId());
            filterSales(postsList, salesList, response.getPosts());
            sort(order, response.getPosts());
        }
        return response;
    }

    private User userGetter(String userId) {
        User user = null;
        try {
            int id = Integer.parseInt(userId);
            user = userRepository.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return user;
    }

    private void sort(String order, List<SaleDTO> sales) {
        if (order.equals(Reference.NAME_ASC))
            new SorterUtil<SaleDTO>().sort(sales, Comparator.comparing(s -> s.getDetail().getProductName()), Reference.ORDER_ASC);
        else if (order.equals(Reference.NAME_DESC))
            new SorterUtil<SaleDTO>().sort(sales, Comparator.comparing(s -> s.getDetail().getProductName()), Reference.ORDER_DESC);
        else if (order.equals(Reference.BEST_DISCOUNT))
            new SorterUtil<SaleDTO>().sort(sales, Comparator.comparing(SaleDTO::getDiscount), Reference.ORDER_DESC);
    }

    // O(n * n) -> not efficient
    private void filterSales(List<Post> posts, List<Sale> sales, List<SaleDTO> salesDTO) {
        for (Post post : posts)
            for (Sale sale : sales)
                if (sale.getId_post() == post.getId_post())
                    salesDTO.add(new SaleDTO(post, sale));
    }
}