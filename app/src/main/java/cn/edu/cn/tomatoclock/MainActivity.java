package cn.edu.cn.tomatoclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout myTabLayout;
    private ViewPager myViewPager;
    private MyPageAdapter myPageAdapter;
    private TabLayout.Tab Item;
    private TabLayout.Tab Statistic;
    private TabLayout.Tab Target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewPager = (ViewPager) findViewById(R.id.viewpager);
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        myTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        myTabLayout.setupWithViewPager(myViewPager);
        myViewPager.setAdapter(myPageAdapter);
        //指定Tab的位置
        Item = myTabLayout.getTabAt(0);
        Statistic = myTabLayout.getTabAt(1);

        //设置Tab的图标，假如不需要则把下面的代码删去
        Item.setIcon(R.drawable.ic_item_list);
        Statistic.setIcon(R.drawable.ic_icon_statics);

    }


}