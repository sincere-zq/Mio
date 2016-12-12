package yanyu.com.mymio.adpter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

import yanyu.com.mymio.bean.Banner;
import yanyu.com.mymio.util.MD5Utils;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

public class TestLoopAdapter extends LoopPagerAdapter {
    private List<Banner> result;

    public void setImgs(List<Banner> result) {
        this.result = result;
        notifyDataSetChanged();
    }

    public TestLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView imageView = new SimpleDraweeView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageURI(Uri.parse(MD5Utils.geturl(result.get(position).md5)));
        return imageView;
    }


    @Override
    public int getRealCount() {
        return result == null ? 0 : result.size();
    }
}
