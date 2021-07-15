package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.DTOs.response.DetailResponseDTO;
import com.meli.desafiospring.exceptions.custom.BadDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@Getter
public class PostResponseDTO {

    Long id_post;
    String date;
    DetailResponseDTO detail;
    String category;
    Double price;

    public PostResponseDTO(Long id_post, String date, DetailResponseDTO detailResponseDTO, String category, Double price) {
        this.id_post = id_post;
        this.date = date;
        this.detail = detailResponseDTO;
        this.category = category;
        this.price = price;
    }

    public Date _getTime() {
        return toCalendar(date);
    }

    private Date toCalendar(String date) {
        Date date_ = null;
        try {
            date_ = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            throw new BadDateFormat(date);
        }
        return date_;
    }

    public Boolean hasPromo() { return false; }
}
