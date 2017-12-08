package com.example.f4cmpro.evdictionaryapp.Zemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.f4cmpro.evdictionaryapp.R;

import java.util.ArrayList;
import java.util.List;

public class RetroAdapter extends ArrayAdapter<Question> {
    private LayoutInflater mInflater;
    private List<Question> mWords;

    public RetroAdapter(Context context){
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
    public Question getItem(int position) {
        return mWords.get(position);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.retrofit_item, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvLink = (TextView) convertView.findViewById(R.id.tv_link);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(mWords.get(position).getTitle());
        holder.tvLink.setText(mWords.get(position).getLink());
        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvLink;
    }

    public void setQuestionList(List<Question> questions){
        mWords.addAll(questions);
        notifyDataSetChanged();
        for (Question item : mWords){
            Log.e("123", "title: " + item.getTitle() + " - Link: " + item.getLink());
        }
    }
}
