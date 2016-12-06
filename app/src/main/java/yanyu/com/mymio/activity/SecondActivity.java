package yanyu.com.mymio.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.Constant;
import yanyu.com.mymio.R;
import yanyu.com.mymio.adpter.NewsAdapter;
import yanyu.com.mymio.base.BaseActivity;
import yanyu.com.mymio.bean.CollectNewsList;
import yanyu.com.mymio.http.HttpArrayCallBack;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.tool.DividerItemDecoration;

public class SecondActivity extends BaseActivity {


    @Bind(R.id.recy)
    RecyclerView recy;
    private NewsAdapter newsAdapter;
    private int page = 1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    public void beforeInitView() {
        useDefaultTitleBarColor = false;
        getData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setRootPadding(recy);
        newsAdapter = new NewsAdapter(this);
        recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recy.setAdapter(newsAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    public void getData() {
        HashMap<String, Object> params = new HashMap();
        params.put("wpuser_id", 1);
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GETCOLLECTNEWSLIST, params, new HttpArrayCallBack<CollectNewsList>() {

            @Override
            public void onSuccess(List<CollectNewsList> result) {
                if (result != null && result.size() > 0) {
                    newsAdapter.settList(result);
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
