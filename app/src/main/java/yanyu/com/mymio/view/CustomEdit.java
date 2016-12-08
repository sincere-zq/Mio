package yanyu.com.mymio.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/7 0007.
 * 登录编辑控件
 */

public class CustomEdit extends LinearLayout {

    private Context context;

    private TextView text;

    private EditText edit;

    private View view;

    public CustomEdit(Context context) {
        this(context, null);
    }

    public CustomEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        init(attrs);
    }

    private void init(AttributeSet attrs) {
//        view = View.inflate(context, R.layout.view_custom_edit, null);
//        text = (TextView) view.findViewById(R.id.tv_left);
//        edit = (EditText) view.findViewById(R.id.et_right);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(lp);
//        addView(view);
//        if (attrs != null) {
//            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CustomEdit);
//            String left_text = t.getString(R.styleable.CustomEdit_left_text);
//            setLeftContent(left_text);
//            String right_hint_text = t.getString(R.styleable.CustomEdit_right_hint);
//            setRightHintContent(right_hint_text);
//            String right_text = t.getString(R.styleable.CustomEdit_right_text);
//            setRightContent(right_text);
//            int left_size = (int) t.getDimension(R.styleable.CustomEdit_left_size, 20);
//            setLeftSize(left_size);
//            int right_size = (int) t.getDimension(R.styleable.CustomEdit_right_text_size, 16);
//            setRightSize(right_size);
//            int left_color = t.getResourceId(R.styleable.CustomEdit_left_color, context.getResources().getColor(R.color.black));
//            setLeftColor(left_color);
//            int right_color = t.getResourceId(R.styleable.CustomEdit_right_text_color, context.getResources().getColor(R.color.black));
//            setRightColor(right_color);
//            int right_hint_color = t.getResourceId(R.styleable.CustomEdit_right_hint_color, context.getResources().getColor(R.color.black_bg));
//            setRightHintColor(right_hint_color);
//            boolean isenable = t.getBoolean(R.styleable.CustomEdit_edit_enable, true);
//            setEnable(isenable);
//            t.recycle();
//        }
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setEnable(true);
//            }
//        });
    }

    /**
     * 设置左侧字体大小
     */
    public void setLeftSize(int size) {
        text.setTextSize(size);
    }

    /**
     * 设置左侧内容
     */
    public void setLeftContent(String content) {
        if (content != null && !content.equals("") && !TextUtils.isEmpty(content))
            text.setText(content);
        else text.setText("");
    }

    /**
     * 设置左侧颜色
     */
    public void setLeftColor(int colorId) {
        text.setTextColor(colorId);
    }

    /**
     * 设置右侧字体大小
     */
    public void setRightSize(int size) {
        edit.setTextSize(size);
    }

    /**
     * 设置右侧内容
     */
    public void setRightContent(String content) {
        if (content != null && !content.equals("") && !TextUtils.isEmpty(content))
            edit.setText(content);
        else edit.setText("");
    }

    /**
     * 设置右侧hint内容
     */
    public void setRightHintContent(String content) {
        if (content != null && !content.equals("") && !TextUtils.isEmpty(content))
            edit.setHint(content);
        else edit.setHint("");
    }

    /**
     * 设置右侧颜色
     */
    public void setRightColor(int colorId) {
        edit.setTextColor(colorId);
    }

    /**
     * 设置右侧hint颜色
     */
    public void setRightHintColor(int colorId) {
        edit.setHintTextColor(colorId);
    }

    /**
     * 获取右侧内容
     */
    public String getRightContent() {
        return edit.getText().toString();
    }

    /**
     * 设置右侧焦点
     */
    public void setEnable(boolean enable) {
        edit.setEnabled(enable);
    }

}
