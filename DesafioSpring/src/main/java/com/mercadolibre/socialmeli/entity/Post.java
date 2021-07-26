package com.mercadolibre.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.socialmeli.dto.PostDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Integer postId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_at")
    private Date date;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "category_id")
    private Integer category;

    @Column(name = "price")
    private Double price;

    public Post(PostDto postDto, Integer productId) {
        this.userId = postDto.getUserId();
        this.date = postDto.getDate();
        this.category = postDto.getCategory();
        this.price = postDto.getPrice();
        this.productId = productId;
    }

}
