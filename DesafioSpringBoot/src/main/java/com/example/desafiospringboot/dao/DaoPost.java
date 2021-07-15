package com.example.desafiospringboot.dao;

import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.model.PromoPost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
            file.write(postsArray.toJSONString()); 
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean writePromoPost(PromoPost post){
        JSONArray postsArray = this.loadPost();
        JSONObject toSave = post.toJson();
        postsArray.add(toSave);
        try (FileWriter file = new FileWriter("post.json")) {
            file.write(postsArray.toJSONString()); 
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public JSONArray fetchPosts(int usr){
        JSONArray allPosts = this.loadPost();
        JSONArray filtered = new JSONArray();
        for (int i = 0; i < allPosts.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) allPosts.get(i);
            Long a = (Long) item.get("userId");
            int b = a.intValue();
            System.out.println("---"+i);
            if(b == usr){
                //System.out.println("removed---- "+item);
                filtered.add(item);
            }
        }   
        return filtered;
    }
    public JSONArray loadPromos(int userId) {
        JSONArray allPosts = this.loadPost();
        JSONArray filtered = new JSONArray();

        for (int i = 0; i < allPosts.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) allPosts.get(i);
            Long a = (Long) item.get("userId");
            int b = a.intValue();
            if(b == userId){
                System.out.println(" urrrent item... "+item);
                if(item.get("hasPromo")!=null){
                    filtered.add(item);
                }
            }
        }
        return filtered;
    }
    public Boolean idOnUse(int id) {
        JSONArray allPosts = this.loadPost();
        for (int i = 0; i < allPosts.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) allPosts.get(i);
            Long a = (Long) item.get("id_post");
            int b = a.intValue();
            if(b == id){
                return true;
            }
        }
        return false;
    }
}
