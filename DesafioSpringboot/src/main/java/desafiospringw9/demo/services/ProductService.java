package desafiospringw9.demo.services;

import desafiospringw9.demo.dtos.FollowedDTO;
import desafiospringw9.demo.dtos.PostDTO;
import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.PostModel;
import desafiospringw9.demo.models.ProductModel;
import desafiospringw9.demo.models.UserModel;
import desafiospringw9.demo.repositories.IProductRepository;
import desafiospringw9.demo.repositories.IUserRepository;
import desafiospringw9.demo.services.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ProductService implements IProductService{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void createPost(PostDTO post) throws ProductIdNotValidException, PostIdNotValidExceptions, UserNotValidException{
        userRepository.validateUser(post.getUserId());
        productRepository.createProduct(PostMapper.productToModel(post.getDetail()));
        productRepository.createPost(PostMapper.postToModel(post));
    }

    @Override
    public FollowedDTO getFollowedPosts(int userId, String order, int daysBefore) throws UserNotValidException{
        userRepository.validateUser(userId);
        List<UserModel> followed = userRepository.getFollowed(userId);
        List<PostDTO> postDTOs = new ArrayList<>();
        Date limitDate = getDateBeforeTwoWeeks(new Date(), daysBefore);

        for(UserModel u:followed){
            List<PostModel> posts = new ArrayList<>();
            try{
                posts = productRepository.getPostsByUserId(u.getUserId());
            }catch (Exception e){
                e.printStackTrace();
            }
            for(PostModel p:posts)
                if(limitDate.compareTo(p.getDate()) <= 0){
                    ProductModel productModel = productRepository.getProductById(p.getProductId());
                    postDTOs.add(PostMapper.postToDTO(p, productModel));
                }
        }
        if(order.equals("date_desc")){
            postDTOs.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
        }
        else if(order.equals("date_asc")){
            postDTOs.sort((p1,p2) -> p1.getDate().compareTo(p2.getDate()));
        }
        return new FollowedDTO(userId, postDTOs);
    }

    @Override
    public PostDTO getPostsByUserId(int userId, String filter) throws UserNotValidException {
        String userName = userRepository.getUserById(userId).getUserName();
        List<PostModel> filteredPosts = new ArrayList<>();
        List<PostDTO> postDTOs = new ArrayList<>();
        try{
            List<PostModel>  posts = productRepository.getPostsByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(PostModel p:filteredPosts){
            ProductModel product = productRepository.getProductById(p.getProductId());
            postDTOs.add(PostMapper.postToDTO(p, product));
        }
        return new PostDTO();
    }

    private Date getDateBeforeTwoWeeks(Date date, int daysBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, daysBefore * -1);
        return calendar.getTime();
    }

}
