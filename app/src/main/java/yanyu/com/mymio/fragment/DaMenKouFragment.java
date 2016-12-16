package yanyu.com.mymio.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yanyu.com.mymio.R;
import yanyu.com.mymio.activity.SecondActivity;
import yanyu.com.mymio.activity.WelcomActivity;
import yanyu.com.mymio.adpter.NewsAdapter;
import yanyu.com.mymio.adpter.TestLoopAdapter;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.bean.Banner;
import yanyu.com.mymio.bean.CollectNewsList;
import yanyu.com.mymio.constant.Constant;
import yanyu.com.mymio.http.HttpArrayCallBack;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.tool.DividerItemDecoration;
import yanyu.com.mymio.util.ToastUtil;
import yanyu.com.mymio.view.TitleBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaMenKouFragment extends BaseFragment {

    private final static int ROLL_DELAY = 1000;
    private RecyclerView recyclerView;
    private TestLoopAdapter pagerAdapter;
    private int page = 1;
    private NewsAdapter newsAdpter;
    private List<CollectNewsList> newsLists;
    private List<Banner> bannerLists;
    private RollPagerView rollPagerView;
    private View view;
    private PullToRefreshLayout pullToRefreshLayout;
    private int state;//0、默认状态 1、下拉刷新 2、上拉刷新
    private TitleBar title_bar;

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
        recyclerView = findViewByIdNoCast(R.id.recyclerView);
        pullToRefreshLayout = findViewByIdNoCast(R.id.pullToRefreshLayout);
        title_bar = findViewByIdNoCast(R.id.title_bar);
    }

    @Override
    protected void initData() {
        newsLists = new ArrayList<>();
        bannerLists = new ArrayList<>();
        setHeader();
        title_bar.setBack(false);
        title_bar.setLeftToActivity(SecondActivity.class);
        title_bar.setRightToActivity(WelcomActivity.class);
        newsAdpter = new NewsAdapter(getActivity());
        newsAdpter.setHeaderView(view);
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
        pullToRefreshLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                state = 1;
                page = 1;
                getNewsList();
                getBanner();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                state = 2;
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
                    if (state == 1) {
                        newsLists.clear();
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                        state = 0;
                    }
                    if (state == 2) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                    newsLists.addAll(result);
                    newsAdpter.settList(newsLists);
                    newsAdpter.notifyDataSetChanged();
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

    /**
     * 头部
     */
    public void setHeader() {
        view = View.inflate(getActivity(), R.layout.header_da_men_kou, null);
        rollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView);
    }

}
