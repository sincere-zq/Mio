package yanyu.com.mymio.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

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
    @Bind(R.id.rollPagerView)
    RollPagerView rollPagerView;
    private TestNomalAdapter mNormalAdapter;
    private int Imgs[] = {R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img5};

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
        rollPagerView.setPlayDelay(1000);
        rollPagerView.setAdapter(mNormalAdapter = new TestNomalAdapter(rollPagerView));
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        mNormalAdapter.setImg(Imgs);
    }

    @OnClick({R.id.desc})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.desc:
                IntentUtils.openActivity(getActivity(), SecondActivity.class);
                break;
        }
    }

    private class TestNomalAdapter extends LoopPagerAdapter {
        int img[] = new int[0];

        public void setImg(int[] img) {
            this.img = img;
            notifyDataSetChanged();
        }

        public TestNomalAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(img[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return img.length;
        }
    }
}
