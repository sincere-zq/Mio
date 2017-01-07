package yanyu.com.mymio.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created on 2017/1/7 0007.
 * Author：zengqiang
 * Description:功能描述
 */


public class ListViewUtils {
    public static void mesureListViewHeight(ListView aama_listView) {
        int totalHeight = 0;
        Adapter mAdapter = aama_listView.getAdapter();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View itemView = mAdapter.getView(i, null, aama_listView);
            itemView.measure(0, 0);
            if (mAdapter.getCount() % 2 != 0) {
                totalHeight += itemView.getMeasuredHeight() * (mAdapter.getCount() + 1) / 2;
            } else {
                totalHeight += itemView.getMeasuredHeight() * (mAdapter.getCount()) / 2;
            }
        }
        ViewGroup.LayoutParams params = aama_listView.getLayoutParams();
        params.height = totalHeight + aama_listView.getDividerHeight();
        aama_listView.setLayoutParams(params);
    }

    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0,  0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
    /**
     * 计算ListView高度
     */
    public static void measureListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int listViewHeight = 0;
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                View view = adapter.getView(i, null, listView);
                view.measure(0, 0);
                listViewHeight += view.getMeasuredHeight();
            }
        }
        listViewHeight += listView.getDividerHeight() * listView.getCount();

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = listViewHeight;
        listView.setLayoutParams(layoutParams);
    }
}
