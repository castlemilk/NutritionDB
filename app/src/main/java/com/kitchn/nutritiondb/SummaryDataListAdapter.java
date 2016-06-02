package com.kitchn.nutritiondb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by benebsworth on 22/05/16.
 */
public class SummaryDataListAdapter extends ArrayAdapter<NutritionalData> {

    Context context;
    int layoutResourceId;
    NutritionalData data[] = null;

    public SummaryDataListAdapter(Context context, int layoutResourceId, NutritionalData[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NutritionalDataHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(data[position].row_type, parent, false);

            holder = new NutritionalDataHolder();
            holder.name = (TextView) row.findViewById(R.id.summaryListNameTextView);
            holder.units = (TextView) row.findViewById(R.id.summaryListUnitsTextView);
            holder.value = (TextView) row.findViewById(R.id.summaryListValueTextView);

            row.setTag(holder);
        }
        else
        {
            holder = (NutritionalDataHolder) row.getTag();
        }

        NutritionalData nutData = data[position];
        holder.name.setText(nutData.name);
        holder.units.setText(nutData.units);
        holder.value.setText(nutData.value);

        return row;
    }

    static class NutritionalDataHolder
    {
        TextView name;
        TextView units;
        TextView value;
    }
}