package cn.edu.cn.tomatoclock;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import cn.edu.cn.tomatoclock.fragment.FragmentItem;
import cn.edu.cn.tomatoclock.fragment.FragmentSta;


public class MyPageAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"事项", "我的记录"};
    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new FragmentItem();
        }
        else {
            return new FragmentSta();
            }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
