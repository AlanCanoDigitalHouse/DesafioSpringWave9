package desafiospringw9.demo.services.mapper;

import desafiospringw9.demo.dtos.PostDTO;
import desafiospringw9.demo.dtos.ProductDTO;
import desafiospringw9.demo.models.PostModel;
import desafiospringw9.demo.models.ProductModel;

public class PostMapper {

    public static ProductModel productToModel(ProductDTO prodDTO) {
        return new ProductModel(
                prodDTO.getProductId(),
                prodDTO.getProductName(),
                prodDTO.getType(),
                prodDTO.getBrand(),
                prodDTO.getColor(),
                prodDTO.getNotes()
        );
    }

    public static PostModel postToModel(PostDTO postDTO) {
        return new PostModel(
                postDTO.getUserId(),
                postDTO.getPostId(),
                postDTO.getDate(),
                postDTO.getDetail().getProductId(),
                postDTO.getCategory(),
                postDTO.getPrice()
        );
    }

    public static PostDTO postToDTO(PostModel postModel, ProductModel productModel) {
        ProductDTO prodDTO = productModelToDTO(productModel);
        return new PostDTO(
                postModel.getUserId(),
                postModel.getPostId(),
                postModel.getDate(),
                prodDTO,
                postModel.getCategory(),
                postModel.getPrice()
        );
    }

    public static ProductDTO productModelToDTO(ProductModel productModel) {
        return new ProductDTO(
                productModel.getProductId(),
                productModel.getProductName(),
                productModel.getType(),
                productModel.getBrand(),
                productModel.getColor(),
                productModel.getNotes()
        );
    }
}
