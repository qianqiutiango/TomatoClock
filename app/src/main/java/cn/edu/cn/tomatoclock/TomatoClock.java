package cn.edu.cn.tomatoclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.security.PublicKey;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.cn.tomatoclock.db.RecordManager;
import cn.edu.cn.tomatoclock.domain.Record;
import cn.edu.cn.tomatoclock.util.Util;

public class TomatoClock extends AppCompatActivity {
    private static final long POTATO_TIME = 60*1000*25;
    private static final long PUNISHMENT_TIME = 16*1000;
    private TimeDownCount timeDownCount;
    private TextView myTextview;
    RecordManager recordManager;
    private Handler handler;
    private final Timer timer = new Timer();
    private TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato_clock);
        TextView show = (TextView) findViewById(R.id.tomatoView);
        Intent get = getIntent();
        final int minute = get.getIntExtra("minute",25);
//        timeDownCount =  new TimeDownCount(minute*1000*60,1000)
        timeDownCount =  new TimeDownCount(minute*1000*60,1000){
            @Override
            public void onFinish() {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(TomatoClock.this);
                builder1.setTitle("提示").setMessage("专注结束！将回到主界面");
                builder1.setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TomatoClock.this,MainActivity.class);
                        startActivity(intent);
                        timeDownCount.cancel();
                    }
                });
                builder1.create();
                builder1.show();
                recordManager = new RecordManager(TomatoClock.this);
                if(recordManager.findByName("专注次数") == null){
                    Record record = new Record("专注次数","0次");
                    recordManager.add(record);
                }
                Record temp1 = recordManager.findByName("专注次数");
                String result1 = Integer.parseInt(temp1.getRecordContent().split("次")[0])+1+"次";
                recordManager.updateByName(temp1.getRecordName(),result1);
                if(recordManager.findByName("专注时间") == null){
                    Record record = new Record("专注时间","0分钟");
                    recordManager.add(record);
                }
                Record temp2 = recordManager.findByName("专注时间");
                String result2 = (Integer.parseInt(temp2.getRecordContent().split("分")[0])+minute)+"分钟";
                recordManager.updateByName(temp2.getRecordName(),result2);
            }
        };


        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 1 && Util.isApplicationBroughtToBackground(TomatoClock.this)){
                    myTextview = new TextView(TomatoClock.this);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TomatoClock.this);
                    builder.setTitle("警告").setView(myTextview);
                    final CountDownTimer countDownTimer = new CountDownTimer(PUNISHMENT_TIME,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            myTextview.setText(millisUntilFinished/1000+"s后专注将失败!");
                            myTextview.setPadding(75,0,0,0);
                        }

                        @Override
                        public void onFinish() {
                            Intent intent = new Intent(TomatoClock.this,MainActivity.class);
                            startActivity(intent);

                        }
                    };
                    builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            countDownTimer.cancel();
                        }
                    });

                    countDownTimer.start();
                    builder.create();
                    builder.show();



                }
                super.handleMessage(msg);
            }
        };



        task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timeDownCount.setClockShow(show);
        timeDownCount.start();
        timer.schedule(task, 2000, 1000);
    }



    public void back(View btn){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setPositiveButton("确认返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //open activity
                Intent back = new Intent(TomatoClock.this,MainActivity.class);
                startActivity(back);
            }
        }).setNegativeButton("继续计时", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timeDownCount != null){
            timeDownCount.cancel();
        }
    }
}