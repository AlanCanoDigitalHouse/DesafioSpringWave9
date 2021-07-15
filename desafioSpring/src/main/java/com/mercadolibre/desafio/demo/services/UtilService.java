package com.mercadolibre.desafio.demo.services;

import com.mercadolibre.desafio.demo.dtos.request.DetailRequestDTO;
import com.mercadolibre.desafio.demo.dtos.request.NewPostDTO;
import com.mercadolibre.desafio.demo.dtos.request.NewPostPromDTO;
import com.mercadolibre.desafio.demo.dtos.response.PublicsResponseDTO;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NoOrderException;
import com.mercadolibre.desafio.demo.models.*;
import com.mercadolibre.desafio.demo.repositories.DataBaseProductsRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UtilService {
    DataBaseProductsRepository dataBaseProductsRepository;
    private Integer id_product_counter = 0;
    private Integer id_post_counter = 0;

    public UtilService(DataBaseProductsRepository dataBaseProductsRepository) {
        this.dataBaseProductsRepository = dataBaseProductsRepository;
    }

    // map a newPostDto object to PublicModel to save
    public PublicModel mapperToPostDto(NewPostDTO newPostDTO) {

        ProductModel productModel = validProductExist(newPostDTO.getDetail());



        return new PublicModel(
                id_post_counter++,
                newPostDTO.getDate(),
//                mapperPoductDto(newPostDTO.getDetail()),
                productModel,
                newPostDTO.getCategory(),
                newPostDTO.getPrice()
        );
    }

    public ProductModel validProductExist(DetailRequestDTO detailRequestDTO){
        final Integer[] id = {-1};
        dataBaseProductsRepository.getProducts().forEach(prod -> {
                if(prod.getProductName().equals(detailRequestDTO.getProductName()) &&
                prod.getBrand().equals(detailRequestDTO.getBrand()) &&
                prod.getColor().equals(detailRequestDTO.getColor()) &&
                prod.getNotes().equals(detailRequestDTO.getNotes()) &&
                prod.getType().equals(detailRequestDTO.getType())){

                    id[0] = prod.getProduct_id();
                }
            });
        if (id[0]>=0)
            return dataBaseProductsRepository.getProduct(id[0]).get();
         else
             return mapperPoductDto(detailRequestDTO);
    }

    // map a detail of a product to a product to save
    public ProductModel mapperPoductDto(DetailRequestDTO detailRequestDTO) {
        ProductModel productModel = new ProductModel(
                id_product_counter++,
                detailRequestDTO.getProductName(),
                detailRequestDTO.getType(),
                detailRequestDTO.getBrand(),
                detailRequestDTO.getColor(),
                detailRequestDTO.getNotes()
        );
        this.dataBaseProductsRepository.addProduct(productModel);
        return productModel;
    }

    // generate a list of posts to reply, from a list of publicsDTO
    public List<PublicsResponseDTO> preparePublicsResponse(List<PublicModel> publics){
        List<PublicsResponseDTO> listResponse = new ArrayList<>();

        publics.forEach(e ->
                listResponse.add(mapperToPublicsResponseDto(e)));
        return listResponse;
    }

    // cast PublicModel to PublicsResponseDTO
    public PublicsResponseDTO mapperToPublicsResponseDto(PublicModel publicModel) {
        return new PublicsResponseDTO(
                publicModel.getId_post(),
                dateToString(publicModel.getDate()),
                publicModel.getDetail(),
                publicModel.getCategory().toString(),
                publicModel.getPrice()

        );
    }

    // convert a date to dd-MM-yyyy format
    public String dateToString(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    // Dozermapper
    // map a newPostDto object to PublicModel to save
    public PublicPromoModel mapperToPostPromDto(NewPostPromDTO newPostPromDTO) {
        ProductModel productModel = validProductExist(newPostPromDTO.getDetail());

        return new PublicPromoModel(
                id_post_counter++,
                newPostPromDTO.getDate(),
//                mapperPoductDto(newPostPromDTO.getDetail()),
                productModel,
                newPostPromDTO.getCategory(),
                newPostPromDTO.getPrice(),
                newPostPromDTO.getHasPromo(),
                newPostPromDTO.getDiscount()
        );
    }

    // order by
    public void orderByDate(String order, List<PublicsResponseDTO> list){
        switch (order){
            case "date_asc":
                list.sort(Comparator.comparing(PublicsResponseDTO::getDate));
                break;
            case "date_desc":
                list.sort(Comparator.comparing(PublicsResponseDTO::getDate).reversed());
                break;
            default:
                throwExceptionOrden(new String[]{"date_asc","date_desc"});
                break;
        }
    }

    public void orderBuyerByName(String order, List<BuyerModel> list){
        switch (order){
            case "name_asc":
                list.sort(Comparator.comparing(BuyerModel::getUserName));
                break;
            case "name_desc":
                list.sort(Comparator.comparing(BuyerModel::getUserName).reversed());
                break;
            default:
                throwExceptionOrden(new String[]{"name_asc","name_desc"});
                break;
        }
    }

    public void orderSellerByName(String order, List<SellerModel> list){
        switch (order){
            case "name_asc":
                list.sort(Comparator.comparing(SellerModel::getUserName));
                break;
            case "name_desc":
                list.sort(Comparator.comparing(SellerModel::getUserName).reversed());
                break;
            default:
                throwExceptionOrden(new String[]{"name_asc","name_desc"});
                break;
        }
    }

    public Long weeksBetween(Date date1, Date date2) {
        long days = ChronoUnit.DAYS.between(date1.toInstant(), date2.toInstant());
        return days / 7 + (days % 7 == 0 ? 0 : 1);
    }

    public void throwExceptionOrden(String[] strs){
        List<String> ordersNames = new ArrayList<>(Arrays.asList(strs));
        throw new NoOrderException("Only the following values are accepted in the 'order' :",ordersNames);
    }

}
