package com.kitchn.nutritiondb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarSearchHandler;
import com.blunderer.materialdesignlibrary.listeners.OnSearchListener;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends com.blunderer.materialdesignlibrary.activities.Activity {

    ParseController parse_db;
    public void onSearchComplete(String text, String objectId) {

        //get full parse object and unpack values:
        Map<String, String> itemDictionary = new HashMap<>();
        ParseObject searchedObj = parse_db.getObject(objectId, "FoodItem");
        Intent intent = new Intent(this,
                MyViewPagerWithTabsFragment.class);
        intent.putExtra("item_name", text);
        itemDictionary.put("name", searchedObj.getString("Name"));
        itemDictionary.put("calorie", searchedObj.getString("Calories"));
        itemDictionary.put("carb_sugar", searchedObj.getString("carb_sugar"));
        itemDictionary.put("carb_fibre", searchedObj.getString("carb_fibre"));
        itemDictionary.put("protein", searchedObj.getString("Protein"));
        itemDictionary.put("fat", searchedObj.getString("Fat"));
        itemDictionary.put("url", searchedObj.getString("pic_url"));

        for (Map.Entry<String, String> e : itemDictionary.entrySet()) {
            System.out.println(e.getKey()+e.getValue());
        }

        intent.putExtra("itemDictionary", (Serializable) itemDictionary);



        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        //TODO: get data from parse server
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        //List<String> foods = new ArrayList<>(Arrays.asList("Apple", "Banana"));
        parse_db = new ParseController();
        parse_db.initialise(this);
        //List<String> itemKeys = parse_db.getItemsWithKey("Name", "FoodItem");
        final Map<String, String> keyMap = parse_db.getItemNamesMap("Name","FoodItem");
        final List<String> itemKeys = new ArrayList<>(keyMap.keySet());
        Log.i("Foods new:", Arrays.toString(itemKeys.toArray()));
        return new ActionBarSearchHandler(this, new OnSearchListener() {

            @Override
            public void onSearched(String searchItem) {
                //TODO: write your code here to route to results
                Log.i("String selected: ", searchItem);
                onSearchComplete(searchItem, keyMap.get(searchItem));


            }

        })
                .enableAutoCompletion()
                .setAutoCompletionSuggestions(itemKeys);
    }

   private List<MainActivityFeature> getFeatures() {
       MainActivityFeature informationPage = new MainActivityFeature();
       informationPage.setHeader(true);
       informationPage.setTitle("Nutritional Data");
       informationPage.setActivity(MyViewPagerWithTabsFragment.class);

       List<MainActivityFeature> objects  = new ArrayList<>();
       objects.add(informationPage);

       return objects;


   }
}
