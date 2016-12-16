package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

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

    private StarTypeAdapter starTypeAdapter;

    private List<StarTag> tagList;

    private List<StarList> starList;

    private StarTypeContentAdapter starContentAdapter;
    private int page = 1;

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
            public void currentType(String type) {
                if (type.equals("全部"))
                    getAllStar();
                else if (type.equals("热门"))
                    getHotStar();
                else
                    getTypeStarList(type);
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

    public void getAllStar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GETSTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    public void getTypeStarList(String type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        params.put("type", type);
        HttpHelper.HttpPostArrayUtil(Constant.GETTYPESTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    public void getHotStar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wpuser_id", 1);
        params.put("page", page);
        HttpHelper.HttpPostArrayUtil(Constant.GETHOTSTARLIST, params, new HttpArrayCallBack<StarList>() {

            @Override
            public void onSuccess(List<StarList> result) {
                if (result != null) {
                    starList.clear();
                    starList.addAll(result);
                    starContentAdapter.settList(starList);
                    starContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
}
