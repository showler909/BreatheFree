package com.robert_showler.breathefree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends ArrayAdapter<History> {

    private List<History> histories = new ArrayList<>();

    public HistoryListAdapter(Context context, int i) {
        super(context, i);
    }

    @Override
    public void add(History object) {
        histories.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public History getItem(int index) {
        return histories.get(index);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ChildViewHolder viewHolder;

        // if view is not created already
        if (view == null) {

            // setting layout of view
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.history_card, parent, false);

            // creating view holder
            viewHolder = new ChildViewHolder();
            viewHolder.date = view.findViewById(R.id.date);
            viewHolder.time = view.findViewById(R.id.time);

            // setting view tag
            view.setTag(viewHolder);
        }

        // if view is already created
        else
            viewHolder = (ChildViewHolder) view.getTag();

        // getting history details
        final History history = getItem(position);
        if (history != null) {
            viewHolder.date.setText(history.getDate());
            viewHolder.time.setText(history.getTime());
        }
        return view;
    }

    public void clearList() {
        histories.clear();
        notifyDataSetChanged();
    }

    private static class ChildViewHolder {
        TextView date, time;
    }
}