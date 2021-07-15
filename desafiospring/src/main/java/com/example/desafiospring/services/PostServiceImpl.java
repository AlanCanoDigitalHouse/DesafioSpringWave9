package com.example.desafiospring.services;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.dtos.responsedtos.SellerWithPostsDTO;
import com.example.desafiospring.exceptions.*;
import com.example.desafiospring.repositories.IPostRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PostServiceImpl implements IPostService{

    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    ISellerService iSellerService;
    @Autowired
    IDetailPostService iDetailPostService;

    public PostServiceImpl(IPostRepository iPostRepository, ISellerService iSellerService){
        this.iPostRepository = iPostRepository;
        this.iSellerService = iSellerService;
    }

    @Override
    public void createNewPost(PostDTO postDTO) throws UserNotFoundByIdException, PostAlreadyExistsException, ProductAlreadyExistsException {
        if(this.iSellerService.existsSeller(postDTO.getUserID())){
            if(this.iPostRepository.findPostById(postDTO.getPostID()) == null){
                if(this.validateProduct(postDTO.getDetail())){
                    this.iPostRepository.newPost(postDTO);
                }else{
                    throw new ProductAlreadyExistsException();
                }
            }else throw new PostAlreadyExistsException();
        }else{
            throw new UserNotFoundByIdException();
        }
    }

    @Override
    public SellerWithPostsDTO getPostFromSeller(Integer sellerId, String order) throws UserNotFoundByIdException, OrderNotExistsException {
        SellerDTO sellerDTO = this.iSellerService.findSellerDTOById(sellerId);
        if(sellerDTO !=null){
            SellerWithPostsDTO seller = new SellerWithPostsDTO(sellerId, sellerDTO.getUsername());
            List<PostDTO> filterList = filterDates(sellerDTO.getPostDTOList());
            if(order != null){
                this.sortDates(filterList, order);
            }
            seller.setListPosts(filterList);
            return seller;
        }else{
            throw new UserNotFoundByIdException();
        }
    }

    private List<PostDTO> filterDates(List<PostDTO> posts){
        LocalDate presentDate = LocalDate.now();
        LocalDate pastDate = presentDate.minusWeeks(2).minusDays(1);

        return posts.stream().filter(postDTO -> postDTO.getPublicationDate().isBefore(presentDate)
                && postDTO.getPublicationDate().isAfter(pastDate))
                .collect(Collectors.toList());
    }

    private void sortDates(List<PostDTO> posts, String order) throws OrderNotExistsException {
        if(order.equals("date_desc")){
            posts.sort(
                    Comparator.comparing(PostDTO::getPublicationDate)
            );
        }else if(order.equals("date_asc")){
            Comparator<PostDTO> comparator = Comparator.comparing(PostDTO::getPublicationDate);
            posts.sort(comparator.reversed());
        }else{
            throw new OrderNotExistsException();
        }
    }

    private Boolean validateProduct(DetailPostDTO detailPostDTO) throws ProductAlreadyExistsException {
        DetailPostDTO detailPostById = this.iDetailPostService.findDetailPostById(detailPostDTO.getProductID());
        if(detailPostById == null){
            return true;
        }else{
            throw new ProductAlreadyExistsException();
        }
    }

}
