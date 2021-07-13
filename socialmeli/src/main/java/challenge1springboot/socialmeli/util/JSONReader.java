package challenge1springboot.socialmeli.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class JSONReader {

    public static File readJSONFile(String path){
        return new File(path);
        //File file = null;
        // try {
        // file = ResourceUtils.getFile(path);
        // file = new File(path);
        // } catch (IOException e) {
        //        e.printStackTrace();
        // }
        //return file;
    }


}
