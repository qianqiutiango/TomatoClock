package cn.edu.cn.tomatoclock.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.util.List;

import cn.edu.cn.tomatoclock.MyArrayAdapter;
import cn.edu.cn.tomatoclock.R;
import cn.edu.cn.tomatoclock.RecordAdapter;
import cn.edu.cn.tomatoclock.db.RecordManager;
import cn.edu.cn.tomatoclock.domain.Clock;
import cn.edu.cn.tomatoclock.domain.Record;

public class FragmentSta extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private ListView listView;
    private RecordAdapter recordAdapter;
    private RecordManager recordManager;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemnt_statistic, container, false);
        listView = (ListView)view.findViewById(R.id.listview_statistic);
        RecordManager recordManager = new RecordManager(getActivity());
        List<Record> list = recordManager.getAll();
        recordAdapter = new RecordAdapter(getActivity(),
                R.layout.record_item,
                (ArrayList<Record>) list);
        listView.setAdapter(recordAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示").setMessage("请确认是否清空当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                recordManager = new RecordManager(getActivity());
                if(listView.getItemAtPosition(position)!=null){
                    Record record = (Record) listView.getItemAtPosition(position);
                    recordManager.updateByName(record.getRecordName(),"0");
                }
                recordAdapter.remove(listView.getItemAtPosition(position));

            }
        }).setNegativeButton("否",null);
        builder.create().show();
        return true;
    }
}
