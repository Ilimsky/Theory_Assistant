package com.example.theoryassistant.activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.theoryassistant.R;

import java.util.ArrayList;

public class HintClickedAdapter extends BaseAdapter {

    private Context context;
    private int layout2;
    private ArrayList<Hint> product_list_clicked;
    Activity activity;

    public HintClickedAdapter(Context context, Activity _activity, int layout2, ArrayList<Hint> product_list_clicked) {
        this.context = context;
        this.layout2 = layout2;
        this.activity = _activity;
        this.product_list_clicked = product_list_clicked;
    }

    @Override
    public int getCount() {
        return product_list_clicked.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list_clicked.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView textViewAnswer;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(layout2, null);
            holder.textViewAnswer = (TextView) row.findViewById(R.id.textView_answer);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        final Hint hint_ = product_list_clicked.get(position);
        holder.textViewAnswer.setText(hint_.getAnswer());
        return row;
    }
}