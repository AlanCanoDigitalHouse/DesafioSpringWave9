package com.socialmeli.helpers;


public interface IMapperHelper<M, D> {

    D mapperModel(M model);

    M mapperDto(D dto);
}
