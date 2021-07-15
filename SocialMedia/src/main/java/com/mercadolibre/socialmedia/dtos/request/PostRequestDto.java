package com.mercadolibre.socialmedia.dtos.request;

import com.mercadolibre.socialmedia.dtos.ProductDto;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PostRequestDto {
    private final String nullMessage = "This field cant be null.";

    @NotNull(message = nullMessage)
    @Min(0)
    private Integer userId;

    private Date date;

    @NotNull(message = nullMessage)
    @Valid
    private ProductDto detail;

    @Min(0)
    @NotNull(message = nullMessage)
    private Integer category;

    @Min(0L)
    @NotNull(message = nullMessage)
    private Double price;

    public PostRequestDto(Integer userId, String date, ProductDto detail, Integer category, Double price) throws ParseException {
        this.userId = userId;
        this.detail = detail;
        this.category = category;
        this.price = price;
        Date date1 = stringToDate(date);
        this.date = date1;
    }
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
    }
}
