package com.example.theoryassistant.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.theoryassistant.R;

import java.util.ArrayList;

public class HintListAdapter extends BaseAdapter {

    public Context context;
    public int layout;
    public ArrayList<Hint> product_list;
    Activity activity;

    public HintListAdapter(Context context, int layout, Activity _activity, ArrayList<Hint> product_list) {
        this.context = context;
        this.layout = layout;
        this.activity = _activity;
        this.product_list = product_list;
    }

    public int getCount() {
        return product_list.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mobile_name, details_mobile;
        CardView product_list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(layout, null);

            holder.mobile_name = row.findViewById(R.id.name_mobile);
            holder.details_mobile = row.findViewById(R.id.details);
            holder.product_list = row.findViewById(R.id.product_list);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        final Hint hint_ = product_list.get(position);
        holder.mobile_name.setText(hint_.getQuestion());
        holder.details_mobile.setText(hint_.getAnswer());
        holder.product_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_name = product_list.get(position).question;
                String details_details = product_list.get(position).answer;

                Intent intent = new Intent(activity, HintClicked.class);
                intent.putExtra("question", name_name);
                intent.putExtra("answer", details_details);
                activity.startActivity(intent);
            }
        });
        return row;
    }
}