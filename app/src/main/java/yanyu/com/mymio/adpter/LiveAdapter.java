package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseListAdpter;
import yanyu.com.mymio.bean.LiveBean;
import yanyu.com.mymio.image.ImageLoader;

/**
 * Created on 2017/1/4 0004.
 * Author：zengqiang
 * Description:功能描述
 */


public class LiveAdapter extends BaseListAdpter<LiveBean, LiveAdapter.ViewHolder> {
    public LiveAdapter(Context context) {
        super(context);
    }

    @Override
    public int getResourceId() {
        return R.layout.item_live;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img_title = findViewByIdNoCast(R.id.img_title);
        viewHolder.tv_title = findViewByIdNoCast(R.id.tv_title);
        viewHolder.tv_zhubo = findViewByIdNoCast(R.id.tv_zhubo);
        viewHolder.tv_time = findViewByIdNoCast(R.id.tv_time);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, LiveBean liveBean, int position) {
        holder.tv_title.setText(liveBean.title);
        holder.tv_time.setText(liveBean.datetime);
        holder.tv_zhubo.setText(liveBean.adminname);
        ImageLoader.getInstance().disPlayImage(holder.img_title,liveBean.cover);

    }

    public class ViewHolder extends BaseListAdpter.ViewHolder {
        SimpleDraweeView img_title;
        TextView tv_title, tv_zhubo, tv_time;
    }
}
