package com.kitchn.nutritiondb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.HashMap;


public class SummaryTab extends Fragment {

    private static final String ARG_TEXT = "text";
    private static HashMap<String, String> itemValueDictionary;
    private ListView listView1;


    public SummaryTab() {
    }

    public static SummaryTab newInstance(String text, HashMap<String, String> itemDictionary) {
        SummaryTab fragment = new SummaryTab();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        itemValueDictionary = itemDictionary;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_tab, container, false);


        // download image from specified URL:
        String url = itemValueDictionary.get("url");
        String units = "g";
        System.out.println("Downloading image from URL: "+url);
        NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.foodImageView);
        imageView.setImageUrl(url, Singleton.getInstance(view.getContext()).getImageLoader());

        String text = "";
        if (getArguments() != null && getArguments().containsKey(ARG_TEXT)) {
            text = getArguments().getString(ARG_TEXT);
            if (TextUtils.isEmpty(text)) text = "";
        }
        NutritionalData nutData[]  = new NutritionalData[]
        {
            new NutritionalData("Energy", "kCal", "100", R.layout.list_item_row_header),
            new NutritionalData("", "kJ", "410", R.layout.list_item_row),
            new NutritionalData("Carbohydrates Total", "g", "xx", R.layout.list_item_row_header),
            new NutritionalData("Sugars", "g", itemValueDictionary.get("carb_sugar"), R.layout.list_item_row_sub),
            new NutritionalData("Fibre", "g", itemValueDictionary.get("carb_fibre"), R.layout.list_item_row_sub),
            new NutritionalData("Protein", "g", itemValueDictionary.get("protein"), R.layout.list_item_row),
            new NutritionalData("Fat", "g", itemValueDictionary.get("fat"), R.layout.list_item_row),
            new NutritionalData("Sodium", "mg", "2", R.layout.list_item_row),
            new NutritionalData("Potassium", "mg", "4", R.layout.list_item_row),
            new NutritionalData("Vitamin C", "mg", "5", R.layout.list_item_row),
            new NutritionalData("Vitamin D", "mg", "10", R.layout.list_item_row),

        };

        SummaryDataListAdapter adapter  = new SummaryDataListAdapter(getContext(),
                                                                    R.layout.list_item_row,
                                                                    nutData);

        listView1 = (ListView)view.findViewById(R.id.factSheetListView);
        listView1.setAdapter(adapter);
                                                                //R.layout.list_item_row,
                                                                //nutData);
//        String KJ = Integer.toString(Integer.parseInt(itemValueDictionary.get("calorie"))*4);
        // Set energy header:
//        ((TextView) view.findViewById(R.id.SummaryEnergyHeaderTextView)).setText("Energy:  ");
//        // Set energy value:
//        ((TextView) view.findViewById(R.id.SummaryEnergyValueTextView))
//                .setText(itemValueDictionary.get("calorie")+"Cals" + "("+KJ+")");
//
//        // Set carb header total:
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateTotalHeader)).setText("Carbohydrates Total:  ");
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateTotalValue)).setText("Carbohydrates");
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateTotalUnits)).setText(units);
//        // Set carb value sugar:
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateSugarHeader)).setText("Carbohydrates - Sugar:  ");
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateSugarValue)).setText(itemValueDictionary.get("carb_sugar"));
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateSugarUnits)).setText(units);
//        // Set carb value fibre:
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateFibreHeader)).setText("Carbohydrates - Fibre:  ");
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateFibreValue)).setText(itemValueDictionary.get("carb_fibre"));
//        ((TextView) view.findViewById(R.id.SummaryCarbohydrateFibreUnits)).setText(units);
//
//        //Set protein value:
//        ((TextView) view.findViewById(R.id.SummaryProteinHeader)).setText("Protein:  ");
//        ((TextView) view.findViewById(R.id.SummaryProteinValue)).setText(itemValueDictionary.get("protein"));
//        ((TextView) view.findViewById(R.id.SummaryProteinUnits)).setText(units);
//
//        //Set fat value:
//        ((TextView) view.findViewById(R.id.SummaryFatHeader)).setText("Fat:  ");
//        ((TextView) view.findViewById(R.id.SummaryFatValue)).setText(itemValueDictionary.get("fat"));
//        ((TextView) view.findViewById(R.id.SummaryFatUnits)).setText(units);

        return view;
    }

}