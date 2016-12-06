package yanyu.com.mymio.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import butterknife.Bind;
import yanyu.com.mymio.R;
import yanyu.com.mymio.base.BaseActivity;
import yanyu.com.mymio.util.ToastUtil;
import yanyu.com.mymio.view.CustomVideoView;

public class WelcomActivity extends BaseActivity {


    @Bind(R.id.video_view)
    CustomVideoView videoView;
    private boolean isPause;

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void beforeInitView() {
        useDefaultTitleBarColor = false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                finish();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.reset();
                ToastUtil.showToast("播放视频出错" + extra);
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause)
            startVideo();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        if (videoView != null)
            videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 播放视频
     */
    public void startVideo() {
        if (videoView != null) {
            videoView.start();
        }
    }

    /**
     * 停止播放
     */
    public void stopVideo() {
        isPause = true;
        if (videoView != null)
            videoView.pause();
    }

}
