package yanyu.com.mymio.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.OnClick;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseActivity;
import yanyu.com.mymio.fragment.DaMenKouFragment;
import yanyu.com.mymio.fragment.MeetStarFragment;
import yanyu.com.mymio.fragment.MyLoveFragment;
import yanyu.com.mymio.fragment.PerCenterFragment;
import yanyu.com.mymio.view.CustomTabSelector;

public class MainActivity extends BaseActivity {


    @Bind(R.id.ll_botom)
    LinearLayout llBotom;
    @Bind(R.id.ll_frags)
    LinearLayout llFrags;
    @Bind(R.id.one)
    CustomTabSelector one;
    @Bind(R.id.two)
    CustomTabSelector two;
    @Bind(R.id.three)
    CustomTabSelector three;
    @Bind(R.id.four)
    CustomTabSelector four;
    private DaMenKouFragment daMenKouFragment;

    private MyLoveFragment myLoveFragment;

    private MeetStarFragment meetStarFragment;

    private PerCenterFragment perCenterFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;

    }

    @Override
    public void beforeInitView() {
    }

    @Override
    public void initView() {
        setRootPadding(llFrags);
    }

    @Override
    public void initData() {
        setFragment(0);
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                initTab();
                setFragment(0);
                one.setTextColor(R.color.yello_normal);
                one.setImgResouce(R.mipmap.damenkou2);
                break;
            case R.id.two:
                initTab();
                setFragment(1);
                two.setTextColor(R.color.yello_normal);
                two.setImgResouce(R.mipmap.woaide2);
                break;
            case R.id.three:
                initTab();
                setFragment(2);
                three.setTextColor(R.color.yello_normal);
                three.setImgResouce(R.mipmap.yueyueyue2);
                break;
            case R.id.four:
                initTab();
                setFragment(3);
                four.setTextColor(R.color.yello_normal);
                four.setImgResouce(R.mipmap.hukoubu2);
                break;
        }
    }

    /**
     * 初始化tabs
     */
    public void initTab() {
        one.setImgResouce(R.mipmap.damenkou1);
        two.setImgResouce(R.mipmap.woaide1);
        three.setImgResouce(R.mipmap.yueyueyue1);
        four.setImgResouce(R.mipmap.hukoubu1);
        one.setTextColor(R.color.white);
        two.setTextColor(R.color.white);
        three.setTextColor(R.color.white);
        four.setTextColor(R.color.white);
    }

    /**
     * 设置fragment
     */
    public void setFragment(int pos) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (pos) {
            case 0:
                if (daMenKouFragment == null) {
                    daMenKouFragment = new DaMenKouFragment();
                    ft.add(R.id.ll_frags, daMenKouFragment);
                } else ft.show(daMenKouFragment);
                break;
            case 1:
                if (myLoveFragment == null) {
                    myLoveFragment = new MyLoveFragment();
                    ft.add(R.id.ll_frags, myLoveFragment);
                } else ft.show(myLoveFragment);
                break;
            case 2:
                if (meetStarFragment == null) {
                    meetStarFragment = new MeetStarFragment();
                    ft.add(R.id.ll_frags, meetStarFragment);
                } else ft.show(meetStarFragment);
                break;
            case 3:
                if (perCenterFragment == null) {
                    perCenterFragment = new PerCenterFragment();
                    ft.add(R.id.ll_frags, perCenterFragment);
                } else ft.show(perCenterFragment);
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     */
    public void hideFragment(FragmentTransaction fragmentTransaction) {
        if (daMenKouFragment != null)
            fragmentTransaction.hide(daMenKouFragment);
        if (meetStarFragment != null)
            fragmentTransaction.hide(meetStarFragment);
        if (myLoveFragment != null)
            fragmentTransaction.hide(myLoveFragment);
        if (perCenterFragment != null)
            fragmentTransaction.hide(perCenterFragment);
    }
}
