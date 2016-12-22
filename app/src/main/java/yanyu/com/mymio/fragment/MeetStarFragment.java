package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.view.MyViewPagerIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetStarFragment extends BaseFragment {

    @Bind(R.id.viewPagerIndicator)
    MyViewPagerIndicator viewPagerIndicator;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private List<String> tabs;

    private List<Fragment> fragments;

    private MeetFragment meetFragment;

    private SelectMeetFragment selectMeetFragment;

    private MeetFragment meetedFragment;

    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected int getResource() {
        return R.layout.fragment_meet_star;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        if (meetedFragment == null)
            meetedFragment = new MeetFragment();
        if (meetFragment == null)
            meetFragment = new MeetFragment();
        if (selectMeetFragment == null)
            selectMeetFragment = new SelectMeetFragment();
        fragments.add(meetFragment);
        fragments.add(selectMeetFragment);
        fragments.add(meetedFragment);
        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments == null ? null : fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments == null ? 0 : fragments.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);
        tabs = new ArrayList<>();
        tabs.add("约星");
        tabs.add("约谁");
        tabs.add("已约");
        viewPagerIndicator.setTabItemTitles(tabs);
        viewPagerIndicator.setViewPager(viewPager, 0);
        viewPagerIndicator.setItemClickEvent();
    }

    @Override
    public void onClick(View v) {

    }
}
