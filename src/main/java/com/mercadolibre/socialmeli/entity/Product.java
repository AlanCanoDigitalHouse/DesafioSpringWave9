package com.mercadolibre.socialmeli.entity;

import com.mercadolibre.socialmeli.dto.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "product_Name")
    private String productName;

    @Column(name = "product_Type")
    private String productType;

    @Column(name = "product_Brand")
    private String productBrand;

    @Column(name = "product_Color")
    private String productColor;

    @Column(name = "product_Notes")
    private String productNotes;

    public Product(ProductDto productDto) {
        this.productName = productDto.getProductName();
        this.productBrand = productDto.getProductBrand();
        this.productType = productDto.getProductType();
        this.productColor = productDto.getProductColor();
        this.productNotes = productDto.getProductNotes();
    }

    public Product(Optional<Product> product) {
        this.productName  = product.get().getProductName();
        this.productType  = product.get().getProductType();
        this.productBrand = product.get().getProductBrand();
        this.productColor = product.get().getProductColor();
        this.productNotes = product.get().getProductNotes();

    }
}
