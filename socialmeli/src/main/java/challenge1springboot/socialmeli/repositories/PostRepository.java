package challenge1springboot.socialmeli.repositories;

import challenge1springboot.socialmeli.DTO.ProductDetailDTO;
import challenge1springboot.socialmeli.DTO.request.NewPostRequestDTO;
import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.Product;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.utils.IDGenerator;
import challenge1springboot.socialmeli.utils.JSONReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    public static final IDGenerator AUTO_INCREMENT_POST = new IDGenerator(Reference.PATH_RESOURCE_POST_ID);
    public static final IDGenerator AUTO_INCREMENT_PRODUCT = new IDGenerator(Reference.PATH_RESOURCE_PRODUCT_ID);

    public Post save(NewPostRequestDTO newPostRequestDTO) {
        Post post = null;
        try {
            List<Post> posts = loadFromJSON();
            Product product = newProduct(newPostRequestDTO.getDetail());
            post = newPost(newPostRequestDTO, product);
            posts.add(post);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(ResourceUtils.getFile(Reference.PATH_RESOURCE_POST), posts);
        } catch (IOException ignored) {
        }
        return post;
    }

    public List<Post> loadFromJSON() throws IOException {
        File file = JSONReader.readJSONFile(Reference.PATH_RESOURCE_POST);
        return mapObject(file);
    }

    public List<Post> listPostPublishedByUser(List<Integer> userIDs) {
        try {
            return loadFromJSON()
                    .stream()
                    .filter(p -> userIDs.contains(p.getUserId())).collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<Post> listPostPublishedByUser(int userId) {
        try {
            return loadFromJSON()
                    .stream()
                    .filter(p -> p.getUserId() == userId).collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Post> mapObject(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Post>> typeReference = new TypeReference<>() {
        };
        List<Post> posts = null;
        posts = objectMapper.readValue(file, typeReference);
        return posts;
    }

    private Product newProduct(ProductDetailDTO productDetail) {
        return
                new Product(AUTO_INCREMENT_PRODUCT.next(),
                        productDetail.getProductName(),
                        productDetail.getType(),
                        productDetail.getBrand(),
                        productDetail.getColor(),
                        productDetail.getNotes());
    }

    private Post newPost(NewPostRequestDTO postDetail, Product product) {
        return
                new Post(AUTO_INCREMENT_POST.next(),
                        postDetail.getUserId(),
                        postDetail.getDate(),
                        product,
                        postDetail.getCategory(),
                        postDetail.getPrice());
    }
}
