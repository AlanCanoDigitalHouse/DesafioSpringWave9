package com.mercadolibre.desafio.demo.services;

import com.mercadolibre.desafio.demo.dtos.request.NewPostDTO;
import com.mercadolibre.desafio.demo.dtos.request.NewPostPromDTO;
import com.mercadolibre.desafio.demo.dtos.response.*;
import com.mercadolibre.desafio.demo.models.*;
import com.mercadolibre.desafio.demo.repositories.DataBaseProductsRepository;
import com.mercadolibre.desafio.demo.services.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    DataBaseProductsRepository dataBaseProductsRepository;
    UtilService utilService;
    UserService userService;

    public ProductService(DataBaseProductsRepository dataBaseProductsRepository, UtilService utilService,  UserService userService) {
        this.dataBaseProductsRepository = dataBaseProductsRepository;
        this.utilService = utilService;
        this.userService = userService;
    }

    public List<UserModel> getListUsers(){
        return userService.getList();
    }

    public void loadDataInList(List<UserModel> usersList){
        this.dataBaseProductsRepository.loadSellers(usersList);
    }

    public void addProduct(ProductModel productModel){
        dataBaseProductsRepository.addProduct(productModel);
    }

    public Optional<ProductModel> getProduct(Integer productId){
        return dataBaseProductsRepository.getProduct(productId);
    }

    public List<ProductModel> getProductList(){
        return this.dataBaseProductsRepository.getProducts();
    }

    // Override methods =====================================
    @Override
    public SuccessfullyResponseDTO createPost(NewPostDTO newPostDTO) {
        UserModel userModel = this.userService.validateUserExist(newPostDTO.getUserId());

        this.dataBaseProductsRepository.addPublic(userModel.getUserId(),
                this.utilService.mapperToPostDto(newPostDTO) );
        return new SuccessfullyResponseDTO(200,"A new post was created successfully");
    }

    @Override
    public PublicsFollowedDTO listPublicsFollowed(Integer buyerId, String order) {
        UserModel userModel = this.userService.validateUserExist(buyerId);
        // date
        List<SellerModel> sellersList = userService.getFollowedList(userModel);
        // obtener la lista de vendedores que yo sigo

        Date date = new Date();
        List<PublicModel> list = this.dataBaseProductsRepository.getPublicsBySellerList(sellersList).stream().filter(pub -> utilService.weeksBetween(pub.getDate(),date)<=2).collect( Collectors.toList() );

        List<PublicsResponseDTO> preparedList = this.utilService.preparePublicsResponse(list);

        if (order==null)
            order = "date_desc";

        this.utilService.orderByDate(order,preparedList);

        return new PublicsFollowedDTO(
                userModel.getUserId(),
                preparedList
        );
    }

    @Override
    public SuccessfullyResponseDTO createPromoPost(NewPostPromDTO newPostPromDTO) {
        UserModel userModel = this.userService.validateUserExist(newPostPromDTO.getUserId());

        this.dataBaseProductsRepository.addPromoPublic(userModel.getUserId(),
                this.utilService.mapperToPostPromDto(newPostPromDTO) );
        return new SuccessfullyResponseDTO(HttpStatus.OK.value(), "A new promo post was created successfully");
    }

    @Override
    public PublicCountResponseDTO countPromoPublic(Integer userId) {
        UserModel userModel = this.userService.validateUserExist(userId);

        return new PublicCountResponseDTO(
                userModel.getUserId(),
                userModel.getUserName(),
                this.dataBaseProductsRepository.getPublicPromoCounter(userModel.getUserId())
        );


    }

    @Override
    public PublicPromoListResponseDTO listPromoPublics(Integer userId) {
        UserModel userModel = this.userService.validateUserExist(userId);

        return new PublicPromoListResponseDTO(
                userModel.getUserId(),
                userModel.getUserName(),
                dataBaseProductsRepository.getPublicsPromo(userModel.getUserId()).stream().filter(PublicPromoModel::getHasPromo).collect(Collectors.toList())
        );
    }

    // End Override methods =====================================


}

