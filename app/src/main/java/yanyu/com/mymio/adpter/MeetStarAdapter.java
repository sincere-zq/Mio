package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseListAdpter;
import yanyu.com.mymio.bean.MeetStar;
import yanyu.com.mymio.image.ImageLoader;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

public class MeetStarAdapter extends BaseListAdpter<MeetStar, MeetStarAdapter.ViewHolder> {

    public MeetStarAdapter(Context context) {
        super(context);
    }

    @Override
    public int getResourceId() {
        return R.layout.item_meet_star;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img_title = findViewByIdNoCast(R.id.img_title);
        viewHolder.tv_title = findViewByIdNoCast(R.id.tv_title);
        viewHolder.tv_addr = findViewByIdNoCast(R.id.tv_addr);
        viewHolder.tv_time = findViewByIdNoCast(R.id.tv_time);
        viewHolder.tv_price = findViewByIdNoCast(R.id.tv_price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, MeetStar meetStar, int position) {
        ImageLoader.getInstance().disPlayImage(holder.img_title, meetStar.index_pic.get(0));
        holder.tv_title.setText(meetStar.title);
        holder.tv_addr.setText(meetStar.address);
        holder.tv_time.setText(meetStar.time_hold);
        holder.tv_price.setText("票价: ￥" + meetStar.min + "元起");
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder {
        SimpleDraweeView img_title;
        TextView tv_title, tv_addr, tv_time, tv_price;
    }
}
