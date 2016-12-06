package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseListAdpter;
import yanyu.com.mymio.bean.MyPlayRecord;
import yanyu.com.mymio.image.ImageLoader;

/**
 * Created by zq on 2016/12/1 0001.
 */

public class PlayRecordAdapter extends BaseListAdpter<MyPlayRecord, PlayRecordAdapter.ViewHolder> {
    private ImageLoader imageLoader;

    public PlayRecordAdapter(Context context) {
        super(context);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getResourceId() {
        return R.layout.item_record;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        holder.img_title = findViewByIdNoCast(R.id.img_title);
        holder.tv_title = findViewByIdNoCast(R.id.tv_title);
        holder.tv_content = findViewByIdNoCast(R.id.tv_content);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, MyPlayRecord myPlayRecord, int position) {
        imageLoader.disPlayImage(holder.img_title, myPlayRecord.cover);
        holder.tv_title.setText(myPlayRecord.title);
        holder.tv_content.setText(myPlayRecord.username);
    }

    public class ViewHolder extends BaseListAdpter.ViewHolder {
        public SimpleDraweeView img_title;
        public TextView tv_title, tv_content;
    }
}
