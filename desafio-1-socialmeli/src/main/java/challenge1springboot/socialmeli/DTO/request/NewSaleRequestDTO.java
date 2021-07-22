package challenge1springboot.socialmeli.DTO.request;

import challenge1springboot.socialmeli.DTO.ProductDetailDTO;
import challenge1springboot.socialmeli.globalconstants.Message;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@Validated
public class NewSaleRequestDTO {

    @Min(value = 0, message = Message.MIN_VALUE + 0)
    private int userId;

    private String date;

    @Valid
    private ProductDetailDTO detail;

    @Min(value = 0, message = Message.MIN_VALUE + 0)
    private int category;

    @DecimalMin(value = "0.1", message = Message.MIN_VALUE + "0.1")
    private double price;

    @BooleanFlag
    private boolean hasPromo;

    @DecimalMin(value = "0.1", message = Message.MIN_VALUE + "0.1")
    private double discount;
}
