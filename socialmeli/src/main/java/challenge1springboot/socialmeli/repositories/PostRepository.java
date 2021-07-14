package challenge1springboot.socialmeli.repositories;

import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.utils.IDGenerator;
import org.springframework.stereotype.Repository;


@Repository
public class PostRepository {

    public static final IDGenerator AUTO_INCREMENT = new IDGenerator(Reference.PATH_RESOURCE_POST_ID);

    public int generateID() {
        return AUTO_INCREMENT.next();
    }


}
