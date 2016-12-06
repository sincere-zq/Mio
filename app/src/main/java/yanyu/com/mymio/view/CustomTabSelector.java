package yanyu.com.mymio.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import yanyu.com.mymio.R;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class CustomTabSelector extends LinearLayout {

    private Context context;

    private View view;

    private ImageView img;

    private TextView text;

    public CustomTabSelector(Context context) {
        this(context, null);
    }

    public CustomTabSelector(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        view = View.inflate(context, R.layout.view_custom_tabs_selecotor, null);
        img = (ImageView) view.findViewById(R.id.img);
        text = (TextView) view.findViewById(R.id.text);
        text.setTextColor(context.getResources().getColor(R.color.white));
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        addView(view);
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CustomTabSelector);
            Drawable d = t.getDrawable(R.styleable.CustomTabSelector_android_src);
            if (d != null)
                img.setImageDrawable(d);
            String s = t.getString(R.styleable.CustomTabSelector_android_text);
            if (s != null)
                text.setText(s);
            t.recycle();
        }
    }

    public void setImgResouce(int imgId) {
        img.setImageResource(imgId);
    }

    public void setTextColor(int textColor) {
        text.setTextColor(context.getResources().getColor(textColor));
    }

    public void setText(String content) {
        text.setText(content);
    }
}
