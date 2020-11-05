package cn.edu.cn.tomatoclock;

import android.annotation.SuppressLint;
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
import cn.edu.cn.tomatoclock.fragment.FragmentItem;

public class MyArrayAdapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";


    public MyArrayAdapter(Context context, int resource, ArrayList<Clock> list) {
        super(context,resource, list);

    }



    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //行控件
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.mylist_item,parent,false);
        }
        Clock clock = (Clock)getItem(position);
        TextView title = itemView.findViewById(R.id.itemTitle);
        TextView detail = itemView.findViewById(R.id.itemDetail);
        title.setText(clock.getName());
        detail.setText(clock.getTime()+"分钟");
        return itemView;
    }
}
