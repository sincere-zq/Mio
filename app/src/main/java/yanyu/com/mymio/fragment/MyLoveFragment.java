package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import yanyu.com.mymio.R;
import yanyu.com.mymio.activity.SecondActivity;
import yanyu.com.mymio.base.BaseFragment;
import yanyu.com.mymio.util.IntentUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLoveFragment extends BaseFragment {

    @Bind(R.id.desc)
    TextView desc;

    @Override
    protected int getResource() {
        return R.layout.fragment_my_love;
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

    @OnClick({R.id.desc})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.desc:
                IntentUtils.openActivity(getActivity(), SecondActivity.class);
                break;
        }
    }

}
