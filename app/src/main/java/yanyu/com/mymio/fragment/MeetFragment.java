package yanyu.com.mymio.fragment;

import android.view.View;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.adpter.MeetStarAdapter;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.bean.MeetStar;
import yanyu.com.mymio.constant.Constant;
import yanyu.com.mymio.callback.HttpArrayCallBack;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.util.ToastUtil;

public class MeetFragment extends BaseFragment {
    @Bind(R.id.recyclerView)
    PullableListView recyclerView;
    @Bind(R.id.pullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    private MeetStarAdapter meetStarAdapter;
    private int page = 1;

    private int state;

    private List<MeetStar> results;

    @Override
    protected int getResource() {
        return R.layout.fragment_meet;
    }

    @Override
    protected void beforeInitView() {
        getMeetStar();
    }


    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        results = new ArrayList<>();
        meetStarAdapter = new MeetStarAdapter(getActivity());
        recyclerView.setAdapter(meetStarAdapter);
        pullToRefreshLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                state = 1;
                page = 1;
                getMeetStar();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                page++;
                state = 2;
                getMeetStar();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void getMeetStar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("is_selling", 1);
        HttpHelper.HttpPostArrayUtil(Constant.GETMEETINGLIST, params, new HttpArrayCallBack<MeetStar>() {

            @Override
            public void onSuccess(List<MeetStar> result) {
                if (result != null) {
                    if (state == 1) {
                        results.clear();
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                        state = 0;
                    }
                    if (state == 2)
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    results.addAll(result);
                    meetStarAdapter.settList(results);
                }
            }

            @Override
            public void onFail(String errMsg) {
                if (state == 1) {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                    state = 0;
                }
                if (state == 2) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
                ToastUtil.showToast(errMsg);
            }
        });
    }
}
