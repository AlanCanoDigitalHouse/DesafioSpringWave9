package com.example.desafiospring.repository;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.PromoPostDTO;
import com.example.desafiospring.dtos.request.PostRequestDTO;
import com.example.desafiospring.dtos.ProductDTO;
import com.example.desafiospring.dtos.request.ProductRequestDTO;
import com.example.desafiospring.dtos.request.PromoPostRequestDTO;
import com.example.desafiospring.dtos.response.PostResponseDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository {

    private List<PostDTO> postDTOList;
    private static Integer postCounter;
    private static Integer productCounter;

    private List<PromoPostDTO> promoPostDTOList;


    PostRepository() {
        postDTOList = new ArrayList<>();
        promoPostDTOList = new ArrayList<>();
        postCounter = 1;
        productCounter = 15;

    }


    @Override
    public PostDTO loadPostDB(PostRequestDTO pPostRequestDTO) {

        PostDTO vPostDTO = new PostDTO(
                pPostRequestDTO.getUserId(),
                postCounter,
                pPostRequestDTO.getDate(),
                loadProductDB(pPostRequestDTO.getDetail()),
                pPostRequestDTO.getCategory(),
                pPostRequestDTO.getPrice()
        );
        postDTOList.add(vPostDTO);
        postCounter++;

        return vPostDTO;
    }

    public PromoPostDTO loadPromoPostDB(PromoPostRequestDTO pPromoPostRequestDTO) {

        PromoPostDTO vPromoPostDTO = new PromoPostDTO(
                pPromoPostRequestDTO.getUserId(),
                postCounter,
                pPromoPostRequestDTO.getDate(),
                loadProductDB(pPromoPostRequestDTO.getDetail()),
                pPromoPostRequestDTO.getCategory(),
                pPromoPostRequestDTO.getPrice(),
                pPromoPostRequestDTO.isHasPromo(),
                pPromoPostRequestDTO.getDiscount()
        );

        promoPostDTOList.add(vPromoPostDTO);
        postCounter++;

        return vPromoPostDTO;
    }

    @Override
    public List<PostResponseDTO> getPostLastTwoWeeks(int userId) {

        List<PostResponseDTO> vListPostResponseDTO = new ArrayList<>();
        LocalDate vDateTwoWeeks = LocalDate.now().minusDays(14);

        for (PostDTO vPostDTO : getPostById(userId)
        ) {
            if (vPostDTO.getDate().compareTo(vDateTwoWeeks) >= 0)
                vListPostResponseDTO.add(new PostResponseDTO(
                        vPostDTO.getId_post(),
                        vPostDTO.getDate(),
                        vPostDTO.getDetail(),
                        vPostDTO.getCategory(),
                        vPostDTO.getPrice()
                ));
        }

        return vListPostResponseDTO;
    }


    private List<PostDTO> getPostById(int userId) {

        List<PostDTO> vListPost = new ArrayList<>();
        for (PostDTO vPost : postDTOList
        ) {
            if (vPost.getUserId() == userId)
                vListPost.add(vPost);
        }
        return vListPost;
    }


    @Override
    public List<PromoPostDTO> getPromoPostsById(int userId) {

        List<PromoPostDTO> vListPost = new ArrayList<>();
        for (PromoPostDTO vPromoPost : promoPostDTOList
        ) {
            if (vPromoPost.getUserId() == userId)
                vListPost.add(vPromoPost);
        }
        return vListPost;
    }


    private ProductDTO loadProductDB(ProductRequestDTO pProductRequestDTO) {

        ProductDTO vProductDTO = new ProductDTO(productCounter, pProductRequestDTO.getProductName(), pProductRequestDTO.getType(),
                pProductRequestDTO.getBrand(), pProductRequestDTO.getColor(), pProductRequestDTO.getNotes());
        productCounter++;

        return vProductDTO;
    }
}


