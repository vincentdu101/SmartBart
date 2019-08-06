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

}
