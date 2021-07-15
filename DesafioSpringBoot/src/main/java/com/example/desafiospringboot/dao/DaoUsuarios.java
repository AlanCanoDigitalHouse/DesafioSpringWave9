package com.example.desafiospringboot.dao;

import com.example.desafiospringboot.model.Usuario;
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
public class DaoUsuarios {
    private JSONParser jsonParser;
    public DaoUsuarios(){
        this.jsonParser = new JSONParser();
    }
    public Usuario getUser(int id){
        JSONArray users = this.loadUsers();
        for (int i = 0; i < users.size(); i++) {
            //System.out.println(userList.get(i).);
            JSONObject item = (JSONObject) users.get(i);
            Long a = (Long) item.get("userId");
            int b = a.intValue();
            //int x = Integer.parseInt(item.get("userId").toString());
            if(b == id){
                return new Usuario(item);
            }
        }
        return null;
    }
    public String saveUser(Usuario usuario){
        JSONObject toSave = usuario.toJson();
        JSONArray users = this.loadUsers();
        users.add(toSave);
        this.writeUsers(users);
        return "";
    } 
    public boolean writeUsers(JSONArray users){
        try (FileWriter file = new FileWriter("usuarios.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(users.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public JSONArray loadUsers(){
        try {
            Object obj = jsonParser.parse(new FileReader("usuarios.json"));
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
    public void loadUsersP(){
        try {
            Object obj = jsonParser.parse(new FileReader("usuarios.json"));
            JSONArray userList = (JSONArray) obj;
            
            for (int i = 0; i < userList.size(); i++) {
                //System.out.println(userList.get(i).);
                JSONObject item = (JSONObject) userList.get(i);
                System.out.println(item.get("userId"));
            }
            
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
    }
}
