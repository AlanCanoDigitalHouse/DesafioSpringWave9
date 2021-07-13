package challenge1springboot.socialmeli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int userId;
    String userName;
    List<Integer> followers;

    public int countFollowers() {
        return this.followers.size();
    }

    public boolean hasFollowers(){
        return this.followers.size() > 0;
    }

    public boolean isFollower(User follower){
        return this.getFollowers().contains(follower.getUserId());
    }
}
