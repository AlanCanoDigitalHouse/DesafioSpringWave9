package com.example.desafiospringboot.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.desafiospringboot.model.Follow;
import com.example.desafiospringboot.model.Usuario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import org.json.simple.parser.ParseException;

@Repository
public class DaoFollow {
    private JSONParser jsonParser;
    public DaoFollow(){
        this.jsonParser = new JSONParser();
    }
    public JSONArray loadFollow(){
        try {
            Object obj = jsonParser.parse(new FileReader("follow.json"));
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
    public boolean createFollow(Follow f){
        JSONArray data = this.loadFollow();
        data.add(f.toJson());
        System.out.println(data);
        try (FileWriter file = new FileWriter("follow.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(data.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean unFollow(Long follower, Long followed){
        JSONArray data = this.loadFollow();
        for (int i = 0; i < data.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) data.get(i);
            Long a = (Long) item.get("follower");
            Long b = (Long) item.get("followed");

            if(a==follower && b==followed){
                data.remove(i);
            }
        }
        try (FileWriter file = new FileWriter("follow.json")) {
            file.write(data.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public List followersList(int usr){
        JSONArray data = this.loadFollow();
        List<Integer> list = new ArrayList <Integer>();
        for (int i = 0; i < data.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) data.get(i);
            Long a = (Long) item.get("follower");
            Long b = (Long) item.get("followed");
            if(b.intValue() == usr){
                list.add(a.intValue());
            }
        }
        return list;
    }
    public List followedList(int usr){
        JSONArray data = this.loadFollow();
        List<Integer> list = new ArrayList <Integer>();
        for (int i = 0; i < data.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) data.get(i);
            Long a = (Long) item.get("follower");
            Long b = (Long) item.get("followed");
            if(a.intValue() == usr){
                list.add(b.intValue());
            }
        }
        return list;
    }
    
}
