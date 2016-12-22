package yanyu.com.mymio.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerCenterFragment extends BaseFragment {


    @Bind(R.id.desc)
    TextView desc;

    @Override
    protected int getResource() {
        return R.layout.fragment_per_center;
    }

    @Override
    protected void beforeInitView() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {
        setOnClick(desc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.desc:
                break;
        }
    }

}
