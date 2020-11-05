package cn.edu.cn.tomatoclock.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cn.tomatoclock.domain.Record;

public class RecordManager {
    private static final String TAG="DBOperate";
    private DBHelper dbHelper;
    private String TBName;
    public RecordManager(Context context) {
        dbHelper = new DBHelper(context);
        TBName = dbHelper.TB_NAME;
    }
    //判断是否存在表格
    public boolean HaveData(String tablename){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        boolean flag=false;
        cursor = db.rawQuery("select name from sqlite_master where type='table' ", null);
        while(cursor.moveToNext()){
            //遍历出表名
            String name = cursor.getString(0);
            if(name.equals(tablename))
            {
                flag = true;
            }
            Log.i(TAG, name);
        }
        if(flag)
        {
            cursor=db.query(tablename,null,null,null,null,null,null);
            //检查是不是空表
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }
        else
            return false;

    }

    /**
     * 添加一个新的记录
     * @param record
     */
    public void add(Record record){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RECORDNAME", record.getRecordName());
        values.put("RECORDCONTENT", record.getRecordContent());
        db.insert(TBName, null, values);
        db.close();
    }




    /**
     * 通过记录名删除一条记录
     * @param RecordName
     */
    public void deleteByName(String RecordName){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBName,"RECORDNAME=?",new String[]{RecordName});
        Log.i(TAG,RecordName+"已删除");
    }

    /**
     * 获取所有的记录
     * @return
     */
    public List<Record> getAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TBName, null,
                null, null,
                null,null, null);
        List<Record> list = new ArrayList<>();
        //取出所有数据
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Record record = new Record();
            record.setRecordName(cursor.getString(cursor.getColumnIndex("RECORDNAME")));
            record.setRecordContent(cursor.getString(cursor.getColumnIndex("RECORDCONTENT")));
            list.add(record);
        }
        cursor.close();

        db.close();
        return list;
    }

    /**
     * 通过记录名查找记录
     * @param name
     * @return
     */

    public Record findByName(String name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TBName, null,
                "RECORDNAME=?", new String[]{name},
                null,null, null);
        Record record = null;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            record = new Record();
            record.setRecordName(cursor.getString(cursor.getColumnIndex("RECORDNAME")));
            record.setRecordContent(cursor.getString(cursor.getColumnIndex("RECORDCONTENT")));
        }
        cursor.close();

        db.close();
        return record;
    }

    /**
     * 通过记录名修改一条记录
     * @param RecordName
     * @param RecordContent
     */
    public void updateByName(String RecordName,String RecordContent){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RECORDCONTENT",RecordContent);
        db.update(TBName,values,"RECORDNAME=?",new String[]{RecordName});
    }






}
