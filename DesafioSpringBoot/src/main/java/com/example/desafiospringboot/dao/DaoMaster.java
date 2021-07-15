package com.example.desafiospringboot.dao;

import org.json.simple.parser.JSONParser;

/**
 * DaoMaster
 */
public abstract class DaoMaster {
    private JSONParser jsonParser;
    public DaoMaster(){
        this.jsonParser = new JSONParser();
    }
}