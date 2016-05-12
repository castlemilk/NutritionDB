package com.kitchn.nutritiondb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;


public class DetailedTab extends Fragment {

    private static final String ARG_TEXT = "text";
    Map<String, String> itemValues;

    public DetailedTab() {
    }

    public static DetailedTab newInstance(String text) {
        DetailedTab fragment = new DetailedTab();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed_tab, container, false);

        String text = "";
        if (getArguments() != null && getArguments().containsKey(ARG_TEXT)) {
            text = getArguments().getString(ARG_TEXT);
            if (TextUtils.isEmpty(text)) text = "";
        }
        // set Energy header value
        ((TextView) view.findViewById(R.id.DetailedEnergyHeaderTextView)).setText("Energy");
        ((TextView) view.findViewById(R.id.DetailedEnergyValueTextView)).setText(text);
        return view;
    }

}