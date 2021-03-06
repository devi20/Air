package com.phatware.android.recotest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phatware.android.model.Gesture;

import java.util.ArrayList;
import java.util.List;


public class CustomGridViewAdapter2 extends ArrayAdapter<Gesture> {
    Context context;
    int layoutResourceId;
    List<Gesture> data = new ArrayList<Gesture>();

    public CustomGridViewAdapter2(Context context, int layoutResourceId,
                                  List<Gesture> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
          //  holder.txtTitle = (TextView) row.findViewById(R.id.item_text2);
            holder.signItem = (TextView) row.findViewById(com.phatware.android.recotest.R.id.textView3);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Gesture item1 = data.get(position);
        //holder.txtTitle.setText(item1.getAction());
        holder.signItem.setText(item1.getSign());
        return row;

    }

    static class RecordHolder {
       // TextView txtTitle;
        TextView signItem;

    }
}