package com.example.DesafioSpring.servicies;

import com.example.DesafioSpring.dtos.PublicationDTO;
import com.example.DesafioSpring.dtos.PublicationResponseDTO;
import com.example.DesafioSpring.dtos.PublicationResponseMsgDTO;
import com.example.DesafioSpring.exceptions.IdleSellerException;
import com.example.DesafioSpring.exceptions.PostCreationException;
import com.example.DesafioSpring.exceptions.UsersDoNotExistException;
import com.example.DesafioSpring.models.Publication;
import com.example.DesafioSpring.repositories.ProductRepository;
import com.example.DesafioSpring.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class ProductService implements IProductService{

    private ProductRepository productRepository;
    private UserRepository userRepository;
    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // 5 - Register a new publication
    @Override
    public PublicationResponseMsgDTO newPost(PublicationDTO publication) {
        PublicationResponseMsgDTO publicationResponseDTO = new PublicationResponseMsgDTO();
        var result = productRepository.findPublicationById(publication.getId_post());

        if (result){
            throw new PostCreationException("/// Publication is already created");
        }else{
            Publication post = new Publication();
            post.setId_post(publication.getId_post());
            post.setUserId(publication.getUserId());
            post.setDate(publication.getDate());
            post.setProductID(publication.getDetail().getProduct_id());
            post.setCategory(publication.getCategory());
            post.setPrice(publication.getPrice());
            productRepository.createNewPost(post);
            publicationResponseDTO.setMessage("Publication created successfully");
        }
        return publicationResponseDTO;
    }


    // 6 - List the posts made by sellers that a user follows in the last two weeks  // 9
    @Override
    public List<PublicationResponseDTO> publicationList(Integer userId, String mode) {
        List<PublicationResponseDTO> publicationResponseDTO = new ArrayList<>();
        var aux = userRepository.findBuyerById(userId);
        if (mode == null) { mode = ""; }
        if (aux){
            List<Integer> sellerIdList;
            sellerIdList = userRepository.getSellerIdList(userId);
            for(Integer sellerId : sellerIdList){
                var result = productRepository.findSellerById(sellerId);
                List<Publication> post = new ArrayList<>();
                if (result){
                    List<Publication> latestPost =  productRepository.findPublicationBySellerID(sellerId);
                    LocalDate now = LocalDate.now();
                    LocalDate twoWeeks = now.minusDays(14);
                    for ( Publication p : latestPost) {
                        if (p.getDate().isAfter(twoWeeks)){
                            post.add(p);
                        }
                    }
                    if (mode.equals("date_asc")){
                        Collections.sort(latestPost, (o1, o2) -> o1.getDate().compareTo(o2.getDate()) );
                    } else if (mode.equals("date_desc")){
                        Collections.sort(latestPost, (o1, o2) -> o2.getDate().compareTo(o1.getDate()) );
                    }
                    PublicationResponseDTO publicationResponseDTO1 = new PublicationResponseDTO();
                    publicationResponseDTO1.setUserId(userId);
                    publicationResponseDTO1.setPosts(post);
                    publicationResponseDTO.add(publicationResponseDTO1);
                }else {
                    throw new IdleSellerException("/// The seller has not posted yet.");
                }
            }
            return publicationResponseDTO;
        }else {
            throw new UsersDoNotExistException("/// The id entered does not correspond to a buyer.");
        }
    }

    public String start() {
        return productRepository.start();
    }
}
