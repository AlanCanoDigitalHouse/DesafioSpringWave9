package com.mercadolibre.desafio1.services;

import com.mercadolibre.desafio1.dto.UserDTO;
import com.mercadolibre.desafio1.dto.request.ProductRequestDTO;
import com.mercadolibre.desafio1.dto.request.PublicationRequestDTO;
import com.mercadolibre.desafio1.dto.response.*;
import com.mercadolibre.desafio1.exceptions.DateAfterNowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;
import com.mercadolibre.desafio1.repositories.interfaces.ProductRepository;
import com.mercadolibre.desafio1.repositories.interfaces.PublicationRepository;
import com.mercadolibre.desafio1.repositories.interfaces.UserRepository;
import com.mercadolibre.desafio1.services.interfaces.PublicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {
    private final ProductRepository productRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public PublicationServiceImpl(ProductRepository productRepository, PublicationRepository publicationRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PublicationResponseDTO newPost(PublicationRequestDTO publicationRequestDTO,Boolean isPromo) throws UserNotExistException, DateAfterNowException {
        if(Objects.isNull(userRepository.getUserById(publicationRequestDTO.getUserId()))) {
            throw new UserNotExistException("El usuario con ID "+publicationRequestDTO.getUserId()+" no existe.");
        }

        if(publicationRequestDTO.getDate().isAfter(LocalDate.now())) {
            throw new DateAfterNowException("No puede ingresar una fecha posterior a la fecha actual.");
        }

        ProductRequestDTO product = publicationRequestDTO.getDetail();
        ProductResponseDTO newProductResponse = productRepository.addProduct(product.getProductName(),
                                                                             product.getType(),
                                                                             product.getBrand(),
                                                                             product.getColor(),
                                                                             product.getNotes());
        Boolean promo = null;
        Double discount = null;
        if(isPromo) {
            promo = true;
            discount = publicationRequestDTO.getDiscount();
        }

        PublicationResponseDTO newPublicationResponse = publicationRepository.addPublication(publicationRequestDTO.getUserId(),
                                                                                             publicationRequestDTO.getDate(),
                                                                                             newProductResponse,
                                                                                             publicationRequestDTO.getCategory(),
                                                                                             publicationRequestDTO.getPrice(),
                                                                                             promo,
                                                                                             discount);
        return newPublicationResponse;
    }

    @Override
    public FollowedUserListResponseDTO getFollowersUserList(Integer userId, String order) throws UserNotExistException {
        if(Objects.isNull(userRepository.getUserById(userId))) {
            throw new UserNotExistException("El usuario con ID "+userId+" no existe.");
        }

        ArrayList<Integer> followsIds = userRepository.getFollowsById(userId);
        ArrayList<PublicationResponseDTO> publicationsTotal = new ArrayList<>();

        for(Integer id:followsIds){
            ArrayList<PublicationResponseDTO> publicationsUser = publicationRepository.getPublicationsByUserId(id,false);

            for(PublicationResponseDTO publication:publicationsUser) {
                PublicationResponseDTO newPublication = new PublicationResponseDTO(publication);
                newPublication.setUserId(null);
                newPublication.setHasPromo(null);
                publicationsTotal.add(newPublication);
            }
        }

        LocalDate maxDate = LocalDate.now();
        LocalDate minDate = maxDate.minusWeeks(2);
        publicationsTotal.sort(Comparator.comparing(PublicationResponseDTO::getDate).reversed());
        publicationsTotal.stream().filter(post -> post.getDate().isAfter(minDate)&& post.getDate().isBefore(maxDate)).collect(Collectors.toList());

        this.orderPublications(publicationsTotal,order);
        return new FollowedUserListResponseDTO(userId,publicationsTotal);
    }

    @Override
    public UserPromoCountResponseDTO getCountProductsPromoByUser(Integer userId) throws UserNotExistException {
        UserDTO user = userRepository.getUserById(userId);

        if(Objects.isNull(user)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId);
        }
        ArrayList<PublicationResponseDTO> promoPublicationsUser = publicationRepository.getPublicationsByUserId(userId,true);

        return new UserPromoCountResponseDTO(userId,user.getUserName(),promoPublicationsUser.size());
    }

    @Override
    public UserPromoListResponseDTO getListProductsPromoByUser(Integer userId) throws UserNotExistException {
        UserDTO user = userRepository.getUserById(userId);

        if(Objects.isNull(user)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId);
        }
        ArrayList<PublicationResponseDTO> promoPublicationsUser = publicationRepository.getPublicationsByUserId(userId,true);

        return new UserPromoListResponseDTO(userId,user.getUserName(),promoPublicationsUser);
    }

    private void orderPublications(ArrayList<PublicationResponseDTO> publicationList, String order){
        if(order.equalsIgnoreCase("date_asc")){
            publicationList.sort(Comparator.comparing(PublicationResponseDTO::getDate));
        }else if(order.equalsIgnoreCase("date_desc")){
            publicationList.sort(Comparator.comparing(PublicationResponseDTO::getDate).reversed());
        }
    }
}
