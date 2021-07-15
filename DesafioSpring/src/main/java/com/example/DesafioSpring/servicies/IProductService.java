package com.example.DesafioSpring.servicies;

import com.example.DesafioSpring.dtos.PublicationDTO;
import com.example.DesafioSpring.dtos.PublicationResponseDTO;
import com.example.DesafioSpring.dtos.PublicationResponseMsgDTO;

import java.util.List;

public interface IProductService {

    PublicationResponseMsgDTO newPost(PublicationDTO publication);
    List<PublicationResponseDTO> publicationList(Integer userId, String mode);

}
