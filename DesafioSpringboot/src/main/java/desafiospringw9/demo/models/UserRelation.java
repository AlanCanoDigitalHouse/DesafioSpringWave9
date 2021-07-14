package desafiospringw9.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRelation {

    private int relationId;
    private int follower;
    private int following;
}
