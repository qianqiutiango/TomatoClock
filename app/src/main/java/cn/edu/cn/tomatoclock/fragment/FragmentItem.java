package cn.edu.cn.tomatoclock.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cn.tomatoclock.MyArrayAdapter;
import cn.edu.cn.tomatoclock.R;
import cn.edu.cn.tomatoclock.TimeDownCount;
import cn.edu.cn.tomatoclock.TomatoClock;
import cn.edu.cn.tomatoclock.domain.Clock;

public class FragmentItem extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String TAG = "TOMATO:";
    private ListView listView;
    private MyArrayAdapter myArrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        listView = (ListView)view.findViewById(R.id.listview1);
        List<Clock> list = getData();
        myArrayAdapter = new MyArrayAdapter(getActivity(),
                R.layout.mylist_item,
                (ArrayList<Clock>) list);
        listView.setAdapter(myArrayAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return view;
    }
    public List<Clock> getData(){
        List<Clock> list = new ArrayList<Clock>();
        String[] items = {"测试","标准番茄钟","两个番茄钟"};
        String[] times = {"1","25","50"};
        for (int i = 0; i < 3; i++) {
            Clock clock = new Clock();
            Map<String, Object> map=new HashMap<String, Object>();
            clock.setName(items[i]);
            clock.setTime(times[i]);
            list.add(clock);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = listView.getItemAtPosition(position);
        Clock clock = (Clock) itemAtPosition;
        int minute = Integer.parseInt(clock.getTime());

        Log.i(TAG,"onItemClick:Name"+clock.getName()+"启动");
        Intent intent = new Intent(getActivity(), TomatoClock.class);
        intent.putExtra("minute",minute);
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"onItemLongClick:对话框事件处理");
                myArrayAdapter.remove(listView.getItemAtPosition(position));
            }
        }).setNegativeButton("否",null);
        builder.create().show();


        return true;
    }
}
