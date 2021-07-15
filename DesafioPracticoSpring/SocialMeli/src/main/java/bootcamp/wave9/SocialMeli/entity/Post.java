package bootcamp.wave9.SocialMeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Post {

    @NotNull
    @Min(value = 1, message = "Invalid userId")
    private int userId;

    @JsonProperty("id_post")
    private int postId;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull
    private Product detail;

    @NotNull
    @PositiveOrZero(message = "Category must be greater than zero.")
    private int category;

    @NotNull
    @PositiveOrZero(message = "Price must be greater than zero.")
    private double price;


    private boolean hasPromo;

    @Min(value = 0, message = "Discount can't be negative.")
    @Max(value = 1, message = "Discount can't be greater than 1.")
    private double discount;

}
