package com.example.desafiospringboot.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.meli.model.Post;
import com.example.meli.model.Usuario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import org.json.simple.parser.ParseException;

@Repository
public class DaoPost {
    private JSONParser jsonParser;
    public DaoPost(){
        this.jsonParser = new JSONParser();
    }
    public JSONArray loadPost(){
        try {
            Object obj = jsonParser.parse(new FileReader("post.json"));
            JSONArray userList = (JSONArray) obj;
            /*
            for (int i = 0; i < userList.size(); i++) {
                //System.out.println(userList.get(i).);
                JSONObject item = (JSONObject) userList.get(i);
                System.out.println(item.get("userId"));
            }
            */
            return userList;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public boolean writePost(Post post){
        JSONArray postsArray = this.loadPost();
        JSONObject toSave = post.toJson();
        postsArray.add(toSave);
        try (FileWriter file = new FileWriter("post.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(postsArray.toJSONString()); 
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public JSONArray fetchPosts(Usuario usr){
        JSONArray allPosts = this.loadPost();
        for (int i = 0; i < allPosts.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) allPosts.get(i);
            Long a = (Long) item.get("idAutor");
            int b = a.intValue();
            if(b != usr.getUserId()){
                allPosts.remove(i);
            }
        }   
        return allPosts;
    }
}
