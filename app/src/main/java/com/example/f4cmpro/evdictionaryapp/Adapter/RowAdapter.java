package com.example.f4cmpro.evdictionaryapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;
import com.example.f4cmpro.evdictionaryapp.R;

import java.util.ArrayList;
import java.util.List;

public class RowAdapter extends ArrayAdapter<Word> implements AdapterDataModel<List<Word>>, AdapterDataView {
    private LayoutInflater mInflater;
    private List<Word> mWords;

    public RowAdapter(Context context) {
        super(context, R.layout.item_row_word);
        mInflater = LayoutInflater.from(context);
        mWords = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (mWords == null || mWords.isEmpty()) {
            return 0;
        }
        return mWords.size();
    }

    @Override
    public Word getItem(int position) {
        return mWords.get(position);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_row_word, parent, false);
            holder = new ViewHolder();
            holder.nameTV = (TextView) convertView.findViewById(R.id.tv_name);
            holder.meanTV = (TextView) convertView.findViewById(R.id.tv_meaning);
            holder.phoneticTV = (TextView) convertView.findViewById(R.id.tv_phonetic);
            holder.typeTV = (TextView) convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTV.setText(mWords.get(position).getName());
        holder.phoneticTV.setText(mWords.get(position).getPhonetic());
        holder.typeTV.setText(mWords.get(position).getType());
        holder.meanTV.setText(mWords.get(position).getMeaning());
        return convertView;
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public List<Word> getList() {
        return mWords;
    }

    @Override
    public void setList(List<Word> list) {
        mWords = list;
    }

    private class ViewHolder {
        TextView nameTV;
        TextView meanTV;
        TextView phoneticTV;
        TextView typeTV;
    }
}
