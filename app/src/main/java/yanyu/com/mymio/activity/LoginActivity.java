package yanyu.com.mymio.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseActivity;
import yanyu.com.mymio.util.IntentUtils;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.img_login)
    ImageView imgLogin;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.tv_login)
    TextView tvLogin;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                IntentUtils.openActivity(this, MainActivity.class);
                break;
        }
    }

}
