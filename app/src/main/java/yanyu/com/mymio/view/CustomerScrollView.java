package yanyu.com.mymio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by zengqiang on 2016/11/4 0004.
 * Description:监听滑动的ScorllView类同
 */

public class CustomerScrollView extends ScrollView {

    private OnScrollChangedListener onScrollChangedListener;

    public CustomerScrollView(Context context) {
        super(context);
    }

    public CustomerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedListener != null)
            onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }
}
