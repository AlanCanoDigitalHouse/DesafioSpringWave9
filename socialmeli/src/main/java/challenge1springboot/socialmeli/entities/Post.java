package challenge1springboot.socialmeli.entities;

import challenge1springboot.socialmeli.utils.DateValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    int id_post;
    int userId;
    String date;
    Product detail;
    int category;
    double price;

    public LocalDate getDateValue(){
        return DateValidator.dateValidFormat(date);
    }
}
