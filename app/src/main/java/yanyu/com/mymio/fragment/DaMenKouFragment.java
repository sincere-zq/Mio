package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.view.MyScrollView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaMenKouFragment extends BaseFragment {


    @Bind(R.id.img_top)
    ImageView imgTop;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.scollView)
    MyScrollView scollView;

    @Override
    protected int getResource() {
        return R.layout.fragment_da_men_kou;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        scollView.setImageView(imgTop);
        scollView.setOnHeaderRefreshListener(new MyScrollView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(MyScrollView view) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}
