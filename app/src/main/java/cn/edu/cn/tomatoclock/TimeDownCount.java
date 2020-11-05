package cn.edu.cn.tomatoclock;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimeDownCount extends CountDownTimer {

    private TextView ClockShow;


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeDownCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public TextView getClockShow() {
        return ClockShow;
    }

    public void setClockShow(TextView clockShow) {
        ClockShow = clockShow;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        ClockShow.setText(formatTime(millisUntilFinished));

    }

    @Override
    public void onFinish() {
        ClockShow.setText(formatTime(0));
    }
    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public static String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }



}
