package yanyu.com.mymio.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import yanyu.com.mymio.R;
import yanyu.com.mymio.util.IntentUtils;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private Context context;

    private View view;

    private TextView tv_title;

    private ImageView img_back;

    private ImageView img_right;

    private boolean isBack = true;

    private Class<?> leftActivity;

    private Class<?> rightActivity;

    private int rightState;

    public TitleBar(Context context) {
        this(context, null);
    }


    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        view = View.inflate(context, R.layout.layout_title_bar, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        img_back = (ImageView) view.findViewById(R.id.img_back);
        img_right = (ImageView) view.findViewById(R.id.img_right);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        addView(view);
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            String title = t.getString(R.styleable.TitleBar_setTitle);
            setTitle(title);
            int leftId = t.getResourceId(R.styleable.TitleBar_setLeftImg, 0);
            int rightId = t.getResourceId(R.styleable.TitleBar_setRightImg, 0);
            setLeftImage(leftId);
            setRightImage(rightId);
            rightState = t.getInt(R.styleable.TitleBar_setRightVisible, 0);
            switch (rightState) {
                case 0:
                    img_right.setVisibility(GONE);
                    break;
                case 1:
                    img_right.setVisibility(VISIBLE);
                    img_right.setOnClickListener(this);
                    break;
            }
            t.recycle();
        }
        img_back.setOnClickListener(this);
    }

    public void setLeftToActivity(Class<?> leftActivity) {
        this.leftActivity = leftActivity;
    }

    public void setRightToActivity(Class<?> rightActivity) {
        this.rightActivity = rightActivity;
    }


    public void setBack(boolean back) {
        isBack = back;
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back) {
            try {
                if (isBack)
                    ((Activity) context).finish();
                if (!isBack && leftActivity != null) {
                    IntentUtils.openActivity(context, leftActivity);
                }
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.img_right) {
            try {
                if (!isBack && rightActivity != null) {
                    IntentUtils.openActivity(context, rightActivity);
                }
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLeftImage(int leftImage) {
        img_back.setImageResource(leftImage);
    }

    public void setRightImage(int rightImage) {
        img_right.setImageResource(rightImage);
    }
}
