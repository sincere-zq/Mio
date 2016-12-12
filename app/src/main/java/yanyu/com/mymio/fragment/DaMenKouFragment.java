package yanyu.com.mymio.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.adpter.NewsAdapter;
import yanyu.com.mymio.adpter.TestLoopAdapter;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.bean.Banner;
import yanyu.com.mymio.bean.CollectNewsList;
import yanyu.com.mymio.constant.Constant;
import yanyu.com.mymio.http.HttpArrayCallBack;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.refresh.PullToRefreshLayout;
import yanyu.com.mymio.tool.DividerItemDecoration;
import yanyu.com.mymio.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaMenKouFragment extends BaseFragment {

    private final static int ROLL_DELAY = 1000;
    @Bind(R.id.rollPagerView)
    RollPagerView rollPagerView;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.xRefresh)
    PullToRefreshLayout xRefresh;
    private TestLoopAdapter pagerAdapter;
    private int page = 1;
    private NewsAdapter newsAdpter;
    private List<CollectNewsList> newsLists;
    private List<Banner> bannerLists;
    private boolean isRefresh;

    @Override
    protected int getResource() {
        return R.layout.fragment_da_men_kou;
    }

    @Override
    protected void beforeInitView() {
        getBanner();
        getNewsList();
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        newsLists = new ArrayList<>();
        bannerLists = new ArrayList<>();
        newsAdpter = new NewsAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(newsAdpter);
        rollPagerView.setPlayDelay(ROLL_DELAY);
        pagerAdapter = new TestLoopAdapter(rollPagerView);
        rollPagerView.setAdapter(pagerAdapter);
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.GRAY, Color.WHITE));
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showToast("点击了图片" + position);
            }
        });
        xRefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                xRefresh = pullToRefreshLayout;
                isRefresh = true;
                bannerLists.clear();
                getBanner();
                page = 1;
                newsLists.clear();
                getNewsList();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                xRefresh = pullToRefreshLayout;
                isRefresh = true;
                page++;
                getNewsList();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取首页Banner
     */
    public void getBanner() {
        HttpHelper.HttpPostArrayUtil(Constant.GET_BANNER, new HashMap<String, Object>(), new HttpArrayCallBack<Banner>() {

            @Override
            public void onSuccess(List<Banner> result) {
                bannerLists.addAll(result);
                pagerAdapter.setImgs(result);
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    /**
     * 获取资讯
     */
    public void getNewsList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GET_NEWSLIST, params, new HttpArrayCallBack<CollectNewsList>() {

            @Override
            public void onSuccess(List<CollectNewsList> result) {
                if (result != null) {
                    newsLists.addAll(result);
                    newsAdpter.settList(newsLists);
                    newsAdpter.notifyDataSetChanged();
                    if (isRefresh)
                        LoadSucceed(PullToRefreshLayout.SUCCEED);
                }
            }

            @Override
            public void onFail(String errMsg) {
                if (isRefresh)
                    LoadSucceed(PullToRefreshLayout.FAIL);
            }
        });
    }

    /**
     * 设置刷新或者夹在更多成功
     */
    public void LoadSucceed(int succeed) {
        xRefresh.refreshFinish(succeed);
        isRefresh = false;
    }
}
