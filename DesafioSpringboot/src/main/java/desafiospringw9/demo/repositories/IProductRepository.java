package desafiospringw9.demo.repositories;

import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import desafiospringw9.demo.models.PostModel;
import desafiospringw9.demo.models.ProductModel;

import java.util.List;

public interface IProductRepository {

    void createProduct(ProductModel productModel) throws ProductIdNotValidException;

    void createPost(PostModel postModel) throws PostIdNotValidExceptions;

    List<PostModel> getPostsByUserId(int userId);

    ProductModel getProductById(int productId);

    PostModel getPostById(int userId);
}
