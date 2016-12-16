package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.StartTagSelecteListener;
import yanyu.com.mymio.base.BaseListAdpter;
import yanyu.com.mymio.bean.StarTag;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class StarTypeAdapter extends BaseListAdpter<StarTag, StarTypeAdapter.ViewHolder> {

    private StartTagSelecteListener listener;

    public void setListener(StartTagSelecteListener listener) {
        this.listener = listener;
    }

    public StarTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getResourceId() {
        return R.layout.item_star_type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img_tag = findViewByIdNoCast(R.id.img_tag);
        viewHolder.tv_content = findViewByIdNoCast(R.id.tv_content);
        viewHolder.item = findViewByIdNoCast(R.id.item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final StarTag s, final int position) {
        holder.tv_content.setText(s.type);
        setSelect(holder, s);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.currentType(s.type);
                for (int i = 0; i < tList.size(); i++) {
                    if (i == position) {
                        tList.get(i).isSelect = true;
                    } else {
                        tList.get(i).isSelect = false;
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder {
        ImageView img_tag;
        TextView tv_content;
        RelativeLayout item;
    }

    public void setSelect(ViewHolder holder, StarTag s) {
        if (s.isSelect) {
            holder.item.setBackgroundResource(R.color.white);
            holder.img_tag.setVisibility(View.VISIBLE);
        } else {
            holder.item.setBackgroundResource(R.color.app_bg);
            holder.img_tag.setVisibility(View.INVISIBLE);
        }
    }
}
