package com.kitchn.nutritiondb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.HashMap;


public class SummaryTab extends Fragment {

    private static final String ARG_TEXT = "text";
    private static HashMap<String, String> itemValueDictionary;

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
        final SummaryTab ctx = this;


        // download image from specified URL:
        String url = itemValueDictionary.get("url");
        System.out.println("Downloading image from URL: "+url);
        NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.foodImageView);
        imageView.setImageUrl(url, Singleton.getInstance(view.getContext()).getImageLoader());

        String text = "";
        if (getArguments() != null && getArguments().containsKey(ARG_TEXT)) {
            text = getArguments().getString(ARG_TEXT);
            if (TextUtils.isEmpty(text)) text = "";
        }
        // Set energy header:
        ((TextView) view.findViewById(R.id.SummaryEnergyHeaderTextView)).setText("Energy:  ");
        // Set energy value:
        ((TextView) view.findViewById(R.id.SummaryEnergyValueTextView))
                .setText(itemValueDictionary.get("calorie"));
        // Set carb header total:
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateTotalHeader)).setText("Carbohydrates Total:  ");
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateTotalValue)).setText("Carbohydrates");
        // Set carb value sugar:
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateSugarHeader)).setText("Carbohydrates - Sugar:  ");
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateSugarValue)).setText(itemValueDictionary.get("carb_sugar"));
        // Set carb value fibre:
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateFibreHeader)).setText("Carbohydrates - Fibre:  ");
        ((TextView) view.findViewById(R.id.SummaryCarbohydrateFibreValue)).setText(itemValueDictionary.get("carb_fibre"));

        //Set protein value:
        ((TextView) view.findViewById(R.id.SummaryProteinHeader)).setText("Protein:  ");
        ((TextView) view.findViewById(R.id.SummaryProteinValue)).setText(itemValueDictionary.get("protein"));

        //Set fat value:
        ((TextView) view.findViewById(R.id.SummaryFatHeader)).setText("Fat:  ");
        ((TextView) view.findViewById(R.id.SummaryFatValue)).setText(itemValueDictionary.get("fat"));

        return view;
    }

}