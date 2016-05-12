package com.kitchn.nutritiondb;

import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benebsworth on 8/05/16.
 */
public class ParseController extends Search {
    boolean initialised = false;




    public void initialise(Context context) {
        if (!initialised)

        {
            //Parse.addParseNetworkInterceptor(new ParseLogInterceptor());
            Parse.initialize(new Parse.Configuration.Builder(context)
                    .applicationId("PARSEDEMO")
                    .server("http://parseserver.spz2muwnum.us-east-1.elasticbeanstalk.com/parse/")
                    .build()
            );
            initialised = true;

        }
    }

    public List<String> getItemsWithKey(String key, String className) {
        List<String> itemsList = new ArrayList<String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        try {
            List<ParseObject> results = query.find();
            for (ParseObject foodObject : results) {
                String foodItem = foodObject.getString(key);
                Log.i("Food:", foodItem);
                if (foodItem != null) {

                    itemsList.add(foodItem);
                }

            }

            if (itemsList != null) {
                Log.i("post search: ", Arrays.toString(itemsList.toArray()));
            }
            return itemsList;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ParseObject getObject(String objectId, String className) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        try {
            return query.get(objectId);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }



    public Map<String, String> getItemNamesMap(String key, String className) {
        Map<String, String> items =  new HashMap<String, String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        try {
            List<ParseObject> results = query.find();
            for (ParseObject item : results) {
                String keyValue = item.getString(key);
                Log.i("Key Value:", keyValue);
                if (keyValue != null) {

                    items.put(keyValue, item.getObjectId());
                }

            }

            if (!items.isEmpty()) {
                //DEBUG code
                Log.i("done: ", "Returning values");
                return items;
            }
            else {
                Log.i("done: ", "failed to fill map");
                return null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }



    }
}

