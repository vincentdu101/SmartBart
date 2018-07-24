package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Train;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by vincentdu on 5/7/17.
 */
public class TrainService extends ResourceService {

//    private static JSONParser jsonParser;

    private static Train createTrainFromMapObject(Map<String, Object> objectMap) {

        return new Train(
                Integer.parseInt(objectMap.get("id").toString()),
                objectMap.get("name").toString(),
                objectMap.get("description").toString(),
                Integer.parseInt(objectMap.get("start_station_id").toString()),
                objectMap.get("train_state").toString(),
                toLocalDateTime(objectMap.get("created_at").toString()),
                toLocalDateTime(objectMap.get("modified_at").toString())
        );
    }

    public static List<Train> findAllTrains() {
        List<Train> output = new ArrayList<>();
        String SQL = "SELECT * FROM train";
        List<Map<String, Object>> trainObject = runJdbcTemplate().queryForList(SQL);
        for (Map<String, Object> objectMap : trainObject) {
            output.add(createTrainFromMapObject(objectMap));
        }
        return output;
    }

    public static List<Train> getAllBartTrains() {
//        try {
//            Object object = jsonParser.parse(new FileReader("../"));
//            JSONObject jsonObject = (JSONObject) object;
//            System.out.println(jsonObject);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return null;
    }

//    package com.test;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Iterator;
//
//    public class JsonSimpleReadExample {
//
//        public static void main(String[] args) {
//
//            JSONParser parser = new JSONParser();
//
//            try {
//
//                Object object= parser.parse(new FileReader("test.json"));
//
//                JSONObject jsonObject = (JSONObject) object;
//                System.out.println(jsonObject);
//
//                String name = (String) jsonObject.get("name");
//                System.out.println(name);
//
//                long age = (Long) jsonObject.get("age");
//                System.out.println(age);
//
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }



}
