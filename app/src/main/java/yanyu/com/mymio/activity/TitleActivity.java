package yanyu.com.mymio.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseActivity;

public class TitleActivity extends BaseActivity {

    private String url;
    private String title;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.collap)
    CollapsingToolbarLayout collap;
    @Bind(R.id.appBar)
    AppBarLayout appBar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_title;
    }

    @Override
    public void beforeInitView() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collap.setTitle(title);
//        Glide.with(this).load(R.mipmap.loading_left).centerCrop().into(img);
        Glide.with(this).load(url).centerCrop().into(img);
    }

    @Override
    public void onClick(View v) {

    }

}
