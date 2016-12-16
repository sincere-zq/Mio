package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.StartTagSelecteListener;
import yanyu.com.mymio.adpter.StarTypeAdapter;
import yanyu.com.mymio.adpter.StarTypeContentAdapter;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.bean.StarList;
import yanyu.com.mymio.bean.StarTag;
import yanyu.com.mymio.constant.Constant;
import yanyu.com.mymio.http.HttpArrayCallBack;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.view.TitleBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLoveFragment extends BaseFragment {


    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.list_type)
    ListView listType;
    @Bind(R.id.list_content)
    ListView listContent;
    @Bind(R.id.pullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    private StarTypeAdapter starTypeAdapter;

    private List<StarTag> tagList;

    private List<StarList> starList;

    private StarTypeContentAdapter starContentAdapter;
    private int page = 1;
    private boolean isFresh;
    private boolean isFirst = true;

    @Override
    protected int getResource() {
        return R.layout.fragment_my_love;
    }

    @Override
    protected void beforeInitView() {
        getAllStar();
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        tagList = new ArrayList<>();
        starList = new ArrayList<>();
        setTag();
        starTypeAdapter = new StarTypeAdapter(getActivity());
        starTypeAdapter.settList(tagList);
        listType.setAdapter(starTypeAdapter);
        starContentAdapter = new StarTypeContentAdapter(getActivity());
        listContent.setAdapter(starContentAdapter);
        starTypeAdapter.setListener(new StartTagSelecteListener() {
            @Override
            public void currentType(final String type) {
                isFirst = false;
                loadMore(type);
            }
        });
        pullToRefreshLayout.setPullDownEnable(false);
        if (isFirst)
            loadMore("全部");
    }

    /**
     * 加载更多
     *
     * @param type
     */
    private void loadMore(final String type) {
        if (type.equals("全部")) {
            page = 1;
            getAllStar();
        } else if (type.equals("热门")) {
            page = 1;
            getHotStar();
        } else {
            page = 1;
            getTypeStarList(type);
        }
        pullToRefreshLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                isFresh = true;
                page++;
                switch (type) {
                    case "全部":
                        getAllStar();
                        break;
                    case "热门":
                        getHotStar();
                        break;
                    default:
                        getTypeStarList(type);
                }
            }
        });
    }

    private void setTag() {
        tagList.add(new StarTag("全部", true));
        tagList.add(new StarTag("热门"));
        tagList.add(new StarTag("演员"));
        tagList.add(new StarTag("歌手"));
        tagList.add(new StarTag("游戏"));
        tagList.add(new StarTag("网红"));
        tagList.add(new StarTag("BOSS"));
        tagList.add(new StarTag("名嘴"));
        tagList.add(new StarTag("体育"));
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取全部明星列表
     */
    public void getAllStar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GETSTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    if (!isFresh)
                        starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                    if (isFresh) {
                        isFresh = false;
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    /**
     * g根据类别获取明星
     *
     * @param type
     */
    public void getTypeStarList(String type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        params.put("type", type);
        HttpHelper.HttpPostArrayUtil(Constant.GETTYPESTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    if (!isFresh)
                        starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                    if (isFresh) {
                        isFresh = false;
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    /**
     * 获得热门明星
     */
    public void getHotStar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GETHOTSTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    if (!isFresh)
                        starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                    if (isFresh) {
                        isFresh = false;
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

}
