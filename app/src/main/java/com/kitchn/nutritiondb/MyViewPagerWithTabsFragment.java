package com.kitchn.nutritiondb;

import android.os.Bundle;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by benebsworth on 9/05/16.
 */
public class MyViewPagerWithTabsFragment extends com.blunderer.materialdesignlibrary.activities.ViewPagerWithTabsActivity{
    HashMap<String, String> itemDictionary;

    @Override
    protected boolean expandTabs() {
        return true;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("item_name");
            if (title != null) {
                setTitle(title);
            }
        }
        return new ActionBarDefaultHandler(this);
    }



    @Override
    public ViewPagerHandler getViewPagerHandler() {
        // Return the Pages of the ViewPager
        itemDictionary = (HashMap<String, String>) getIntent().getSerializableExtra("itemDictionary");
        for (Map.Entry<String, String> e : itemDictionary.entrySet()) {
            System.out.println("in pager view: "+e.getKey()+" : "+e.getValue());
        }

        return new ViewPagerHandler(this)
                .addPage(R.string.section1, SummaryTab.newInstance("Summary", itemDictionary))
                .addPage(R.string.section2, DetailedTab.newInstance("Detailed"));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }
}
