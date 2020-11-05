package cn.edu.cn.tomatoclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cn.edu.cn.tomatoclock.domain.Clock;
import cn.edu.cn.tomatoclock.domain.Record;

public class RecordAdapter extends ArrayAdapter {
    public RecordAdapter(@NonNull Context context, int resource, ArrayList<Record> list) {
        super(context, resource,list);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //行控件
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.record_item,parent,false);
        }
        Record record = (Record) getItem(position);

        TextView title = itemView.findViewById(R.id.recordTitle);
        TextView detail = itemView.findViewById(R.id.recordDetail);
        title.setText(record.getRecordName());
        detail.setText(record.getRecordContent());
        return itemView;
    }
}
