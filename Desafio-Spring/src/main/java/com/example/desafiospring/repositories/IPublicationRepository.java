package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.Product;
import com.example.desafiospring.dtos.general.Publication;

import java.util.List;

public interface IPublicationRepository {

    void updatePublicationFile();

    List<Publication> getPosts( Integer userId);

}
