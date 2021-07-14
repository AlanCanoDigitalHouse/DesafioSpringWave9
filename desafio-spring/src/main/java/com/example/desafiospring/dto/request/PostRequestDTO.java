package com.example.desafiospring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequestDTO {
    @NotNull(message = "userId is null")
    @Min(message = "userId has to be greater than zero", value = 0L)
    private Long userId;
    /*@NotNull(message = "idPost is null")
    @NotBlank(message = "idPost is empty")
    @Min(message = "idPost has to be greater than zero", value = 0L)
    @JsonProperty("id_post")
    private Long idPost;*/
    @NotNull(message = "date is null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @Valid
    @NotNull(message = "detail is null")
    private ProductRequestDTO detail;
    @NotNull(message = "category is null")
    private Integer category;
    @NotNull(message = "price is null")
    @DecimalMin(message = "price must be greater than 0", value = "0.1")
    private BigDecimal price;

    private Boolean hasPromo;
    private BigDecimal discount;

    public PostRequestDTO() {
    }

    public PostRequestDTO(Long userId, Date date, ProductRequestDTO detail, Integer category, BigDecimal price) {
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public PostRequestDTO(Long userId, Date date, ProductRequestDTO detail, Integer category, BigDecimal price, Boolean hasPromo, BigDecimal discount) {
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Long getUserId(Long getUserId) {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductRequestDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductRequestDTO detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
