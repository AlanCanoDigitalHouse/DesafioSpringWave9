package testingchallengue.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private String message;
    private String name;
}
