package com.mercadolibre.desafio1.services;

import com.mercadolibre.desafio1.dto.UserDTO;
import com.mercadolibre.desafio1.dto.request.ProductRequestDTO;
import com.mercadolibre.desafio1.dto.request.PublicationRequestDTO;
import com.mercadolibre.desafio1.dto.response.FollowedUserListResponseDTO;
import com.mercadolibre.desafio1.dto.response.ProductResponseDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;
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
    public PublicationResponseDTO newPost(PublicationRequestDTO publicationRequestDTO) throws UserNotExistException, DateAfterNowException {
        if(Objects.isNull(userRepository.getUserById(publicationRequestDTO.getUserId()))) {
            throw new UserNotExistException("El usuario con ID "+publicationRequestDTO.getUserId()+" no existe.");
        }

        if(publicationRequestDTO.getDate().isAfter(LocalDate.now())) {
            throw new DateAfterNowException("No puede ingresar una fecha posterior a la fecha actual.");
        }

        //enviar objeto entero al repository
        ProductRequestDTO product = publicationRequestDTO.getDetail();
        ProductResponseDTO newProductResponse = productRepository.addProduct(product.getProductName(),
                                                                             product.getType(),
                                                                             product.getBrand(),
                                                                             product.getColor(),
                                                                             product.getNotes());

        PublicationResponseDTO newPublicationResponse = publicationRepository.addPublication(publicationRequestDTO.getUserId(),
                                                                                             publicationRequestDTO.getDate(),
                                                                                             newProductResponse,
                                                                                             publicationRequestDTO.getCategory(),
                                                                                             publicationRequestDTO.getPrice());

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
            ArrayList<PublicationResponseDTO> publicationsUser = publicationRepository.getPublicationsByUserId(id);

            for(PublicationResponseDTO publication:publicationsUser) {
                PublicationResponseDTO newPublication = new PublicationResponseDTO(publication);
                newPublication.setUserId(null);
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

    private void orderPublications(ArrayList<PublicationResponseDTO> publicationList, String order){
        if(order.equalsIgnoreCase("date_asc")){
            publicationList.sort(Comparator.comparing(PublicationResponseDTO::getDate));
        }else if(order.equalsIgnoreCase("date_desc")){
            publicationList.sort(Comparator.comparing(PublicationResponseDTO::getDate).reversed());
        }
    }
}
