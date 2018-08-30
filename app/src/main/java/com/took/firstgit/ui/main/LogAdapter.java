package com.took.firstgit.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.took.firstgit.R;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private List<String> _lists;
    private Context _context;

    public LogAdapter(Context context) {
        this._context = context;
        _lists = new ArrayList<>();
    }

    public void onLoadMoreData(List<String> lists) {
        _lists.addAll(lists);
        notifyDataSetChanged();
    }

    public void onRefreshData(List<String> lists) {
        if(_lists != null ) _lists.clear();
        _lists = lists;
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        _lists.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(_context).inflate(R.layout.log_list_item, parent, false);
        return new LogViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {

        holder.logView.setText(_lists.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return _lists != null && !_lists.isEmpty() ? _lists.size() : 0;
    }

    public class LogViewHolder extends RecyclerView.ViewHolder{
        public TextView logView;

        public LogViewHolder(View itemView) {
            super(itemView);
            logView = itemView.findViewById(R.id.log_text);
        }
    }
}
