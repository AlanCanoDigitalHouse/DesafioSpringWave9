package com.mercadolibre.socialmeli.converter;

import com.mercadolibre.socialmeli.dto.PostDto;
import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewPostResquesToPostConverter implements Converter<NewPostRequest, PostDto> {
    @Override
    public PostDto convert(NewPostRequest newPostRequest) {
        PostDto p = new PostDto(newPostRequest.getUserId(),newPostRequest.getDate(),newPostRequest.getCategory(),newPostRequest.getPrice());
        return p;

    }
}
