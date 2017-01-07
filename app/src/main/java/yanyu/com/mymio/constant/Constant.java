package yanyu.com.mymio.constant;

import android.os.Environment;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class Constant {
    public static final String KEY = "a06c2ff7467c7f15b8c594c1d44d8f78";
    public static final String URL = "http://app-service.wopu.me";
    //文件下载目录
    public static final String DOWNLOAD_FILE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mio/download/";
    //    public static final String GETVIEWVIDEOLIST = URL + "/Common.wp/Video/getViewVideoList";//获取视频观看记录
    public static final String GETVIEWVIDEOLIST = "/Common.wp/Video/getViewVideoList";//获取视频观看记录
    public static final String GETCOLLECTNEWSLIST = URL + "/Common.wp/News/getCollectNewsList";//获得用户收藏的咨询列表
    public static final String GET_BANNER = URL + "/Common.wp/Index/getIndexBanner"; //查询banner数据
    public static final String GET_NEWSLIST = URL + "/Common.wp/News/getNewsList"; //获取资讯列表
    public static final String GETSTARLIST = URL + "/Common.wp/Star/getStarList";//获取全部明星列表
    public static final String GETTYPESTARLIST = URL + "/Common.wp/Star/getTypeStarList";//获取某类型明星列表
    public static final String GETHOTSTARLIST = URL + "/Common.wp/Star/getHotStarList";//获取热门明星列表
    public static final String GETMEETINGLIST = URL + "/Common.wp/Meeting/getMeetingList";//获得约星列表
    public static final String GETVOTESTARLIST = URL + "/Common.wp/Star/getVoteStarList";//获得约谁列表
    public static final String VIDEOURL = "http://show-video.wopu.me/c20dfcd97571bf0adfd8955b272abd82.mp4";
    public static final String GET_LIVE_LIST = URL + "/Common.wp/LiveVideo/getVideoList"; //获取直播全部内容
}
