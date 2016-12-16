package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseListAdpter;
import yanyu.com.mymio.bean.StarList;
import yanyu.com.mymio.image.ImageLoader;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class StarTypeContentAdapter extends BaseListAdpter<StarList, StarTypeContentAdapter.ViewHolder> {

    public StarTypeContentAdapter(Context context) {
        super(context);
    }

    @Override
    public int getResourceId() {
        return R.layout.item_star_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img_head = findViewByIdNoCast(R.id.img_head);
        viewHolder.item = findViewByIdNoCast(R.id.item);
        viewHolder.tv_name = findViewByIdNoCast(R.id.tv_name);
        viewHolder.tv_desc = findViewByIdNoCast(R.id.tv_desc);
        viewHolder.tv_focus = findViewByIdNoCast(R.id.tv_focus);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final StarList starList, int position) {
        ImageLoader.getInstance().disPlayImage(holder.img_head, starList.head_pic);
        holder.tv_name.setText(starList.starname);
        holder.tv_desc.setText("粉丝:" + starList.follow_num);
        isFocus(holder, starList);
        holder.tv_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (starList.is_follow == 0)
                    starList.is_follow = 1;
                else if (starList.is_follow == 1)
                    starList.is_follow = 0;
                isFocus(holder, starList);
            }
        });
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder {
        RelativeLayout item;
        SimpleDraweeView img_head;
        TextView tv_name, tv_desc, tv_focus;
    }

    public void isFocus(ViewHolder holder, StarList starList) {
        if (starList.is_follow == 0) {
            holder.tv_focus.setText("关注");
            holder.tv_focus.setBackgroundResource(R.drawable.background_yellow);
        } else if (starList.is_follow == 1) {
            holder.tv_focus.setText("已关注");
            holder.tv_focus.setBackgroundResource(R.drawable.background_black_white);
        }
    }
}
