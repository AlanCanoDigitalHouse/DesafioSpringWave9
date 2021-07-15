package com.example.desafiospring.utilities;

import com.example.desafiospring.dtos.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FakeBD{

    private List<BuyerDTO> buyerDTOList = new ArrayList<>();
    private List<PostDTO> postDTOList = new ArrayList<>();
    private List<DetailPostDTO> productList = new ArrayList<>();
    private List<SellerDTO> sellerDTOList = new ArrayList<>();
    private String[] sellersNames = {
            "Aryana Benitez", "Brendon Villarreal",
            "Milo Dawson", "Dwayne Jones", "Jaquan Cantrell"};
    private String[] buyersNames = {
            "Lia Baxter", "Maxwell Daniel", "Brooklynn Ponce",
            "Eli Brennan", "Roderick Hull",
    };

    //Products
    private String[] productNames = {
        "Laptop","Mouse","Bed","Keyboard","Audi A8","Chair","Viper","Radio Control Car","PC GAMER","Anime Manga"
    };
    private String[] colors = { "Burly Wood","Maroon","Azure","Old Lace","Pale Violet Red","Dark Blue","Mint Cream","Pale Goldenrod","Indigo","red" };
    private String[] productType = { "Electronics","Computers","Smart Home","Arts & Craft","Automotive","Baby","Toys","Software","Cars","Homes"};
    private String[] brands = { "Dell","Apple","Asus","Lenovo","Thermaltake","Audi","Sony","Samsung","Smok","Toyota","Suzuki"};

    //Posts
    private String[] postType = {"New","Old"};
    private Integer[] postCategory = {1,2,3,4,5,6,7,8,9,10};

    public void init(){
        this.loadSellers();
        this.loadPostsList();
        this.loadPostToSellers();
        this.loadBuyers();
    }

    //STEP1
    public void loadSellers(){
        for(int i=0;i<5;i++){
            SellerDTO sellerDTO = new SellerDTO(i,this.sellersNames[i]);
            this.sellerDTOList.add(sellerDTO);
        }
    }

    //STEP2
    private void loadBuyers(){
        for(int i=0;i<5;i++){
            BuyerDTO buyerDTO = new BuyerDTO(i,this.buyersNames[i]);
            this.buyerDTOList.add(buyerDTO);
        }
    }

    //STEP3
    private void loadPostsList(){
        for(int i=0;i<5;i++){
            PostDTO postDTO = new PostDTO();
            postDTO.setPostID(i);
            postDTO.setCategory(this.postCategory[i]);
            postDTO.setPrice(100.0);
            postDTO.setDetail(this.creatProduct(i));
            postDTO.setPublicationDate(LocalDate.now());
            this.postDTOList.add(postDTO);
        }
    }

    //STEP4
    //ADD 1 of 5 loaded posts to all sellers
    private void loadPostToSellers(){
        for(int i=0;i<5;i++){
            List<PostDTO> posts = new ArrayList<>();
            PostDTO post = this.postDTOList.get(i);
            post.setUserID(i);
            posts.add(post);
            this.sellerDTOList.get(i).setPostDTOList(posts);
        }
    }

    private DetailPostDTO creatProduct(Integer index){
        DetailPostDTO detailPostDTO = new DetailPostDTO();
        detailPostDTO.setProductID(index);
        detailPostDTO.setProductName(this.productNames[index]);
        detailPostDTO.setBrand(this.brands[index]);
        detailPostDTO.setColor(this.colors[index]);
        detailPostDTO.setType(this.productType[index]);
        detailPostDTO.setNotes("Note");
        productList.add(detailPostDTO);
        return detailPostDTO;
    }
}
