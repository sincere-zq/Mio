package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.adpter.LiveAdapter;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.bean.LiveBean;
import yanyu.com.mymio.callback.HttpArrayCallBack;
import yanyu.com.mymio.constant.Constant;
import yanyu.com.mymio.http.HttpHelper;
import yanyu.com.mymio.view.CustomListView;
import yanyu.com.mymio.view.TitleBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerCenterFragment extends BaseFragment {


    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.listView)
    CustomListView listView;
    private LiveAdapter adapter;

    @Override
    protected int getResource() {
        return R.layout.fragment_per_center;
    }

    @Override
    protected void beforeInitView() {
        getLiveList();
    }


    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        adapter = new LiveAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    private void getLiveList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", 1);
        HttpHelper.HttpPostArrayUtil(Constant.GET_LIVE_LIST, params, new HttpArrayCallBack<LiveBean>() {

            @Override
            public void onSuccess(List<LiveBean> result) {
                if (result != null)
                    adapter.settList(result);
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
