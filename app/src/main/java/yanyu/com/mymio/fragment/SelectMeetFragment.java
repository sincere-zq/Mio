package yanyu.com.mymio.fragment;

import android.view.View;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableListView;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseFragment;

public class SelectMeetFragment extends BaseFragment {
    @Bind(R.id.recyclerView)
    PullableListView recyclerView;
    @Bind(R.id.pullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    @Override
    protected int getResource() {
        return R.layout.fragment_select_meet;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

}
