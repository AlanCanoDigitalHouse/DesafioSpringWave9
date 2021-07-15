package desafiospringw9.demo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import desafiospringw9.demo.models.PostModel;
import desafiospringw9.demo.models.ProductModel;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    private List<ProductModel> productModels;
    private List<PostModel> postModels;

    public ProductRepository() {
        this.productModels = loadProductsDB();
        this.postModels = loadPostDB();
    }


    @Override
    public void createProduct(ProductModel productModel) throws ProductIdNotValidException {
        validateProductId(productModel.getProductId());
        this.productModels.add(productModel);
    }

    @Override
    public void createPost(PostModel postModel) throws PostIdNotValidExceptions {
        //validatePostId(postModel.getPostId());
        this.postModels.add(postModel);
    }

    @Override
    public PostModel getPostById(int postId){
        try{
            return postModels.stream().filter(p -> postId == p.getPostId()).findFirst().get();
        } catch (Exception e){
            return null;
        }
    }



    @Override
    public List<PostModel> getPostsByUserId(int userId){
        List<PostModel> posts = new ArrayList<>();

        for(PostModel p:this.postModels){
            if(p.getUserId() == userId){
                posts.add(p);
            }
        }
        return posts;
    }


    @Override
    public ProductModel getProductById(int productId) {
        for(ProductModel p:productModels){
            if(p.getProductId() == productId){
                return p;
            }
        }
        return null;
    }





    private boolean validatePostId(int postId) throws PostIdNotValidExceptions {
        for (PostModel p : this.postModels) {
            if (p.getPostId() == postId) {
                throw new PostIdNotValidExceptions(postId);
            }
        }
        return true;
    }

    private boolean validateProductId(int productId) throws ProductIdNotValidException {
        for (ProductModel p : this.productModels) {
            if (p.getProductId() == productId) {
                throw new ProductIdNotValidException(productId);
            }
        }
        return true;
    }


    //obtengo mi db de productos
    private List<ProductModel> loadProductsDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:products.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductModel>> typeRef = new TypeReference<>() {
        };

        List<ProductModel> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    //obtengo mi db de post
    private List<PostModel> loadPostDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostModel>> typeRef = new TypeReference<>() {
        };

        List<PostModel> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }
}
