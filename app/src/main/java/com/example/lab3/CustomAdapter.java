package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Андрій Моружко on 10.10.2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    String Item[];
    String SubItem[];
    int flags[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] Item, String[] SubItem) {
        this.context = context;
        this.Item = Item;
        this.SubItem = SubItem;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return Item.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(android.R.layout.simple_list_item_2, null);
        TextView item = (TextView) view.findViewById(android.R.id.text1);
        TextView subitem = (TextView) view.findViewById(android.R.id.text2);
        item.setText(Item[i]);
        subitem.setText(SubItem[i]);
        return view;
    }
}
