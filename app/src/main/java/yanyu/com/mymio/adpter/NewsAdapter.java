package yanyu.com.mymio.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseRecyclerViewAdapter;
import yanyu.com.mymio.bean.CollectNewsList;
import yanyu.com.mymio.image.ImageLoader;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class NewsAdapter extends BaseRecyclerViewAdapter<CollectNewsList, NewsAdapter.ViewHolder> {
    private ImageLoader imageLoader;

    public NewsAdapter(Context context) {
        super(context);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onMyCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_news, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindMyViewHolder(ViewHolder holder, int position, CollectNewsList bean) {
        holder.tv_title.setText(bean.title);
        holder.tv_content.setText(bean.intro);
        imageLoader.disPlayImage(holder.img_news, bean.cover);
    }

    public class ViewHolder extends BaseRecyclerViewAdapter.Holder {
        SimpleDraweeView img_news;
        TextView tv_title, tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            img_news = (SimpleDraweeView) itemView.findViewById(R.id.img_news);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
