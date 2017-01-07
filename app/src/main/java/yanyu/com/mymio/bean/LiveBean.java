package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2017/1/4 0004.
 * Author：zengqiang
 * Description:功能描述
 */


public class LiveBean implements Parcelable {
    public int video_id;

    public String title;

    public String cover;

    public String datetime;

    public int duration;

    public int status;

    public int wpadmin_id;

    public String adminname;

    @Override
    public String toString() {
        return "LiveBean{" +
                "video_id=" + video_id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", datetime='" + datetime + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                ", wpadmin_id=" + wpadmin_id +
                ", adminname='" + adminname + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.video_id);
        dest.writeString(this.title);
        dest.writeString(this.cover);
        dest.writeString(this.datetime);
        dest.writeInt(this.duration);
        dest.writeInt(this.status);
        dest.writeInt(this.wpadmin_id);
        dest.writeString(this.adminname);
    }

    public LiveBean() {
    }

    protected LiveBean(Parcel in) {
        this.video_id = in.readInt();
        this.title = in.readString();
        this.cover = in.readString();
        this.datetime = in.readString();
        this.duration = in.readInt();
        this.status = in.readInt();
        this.wpadmin_id = in.readInt();
        this.adminname = in.readString();
    }

    public static final Parcelable.Creator<LiveBean> CREATOR = new Parcelable.Creator<LiveBean>() {
        @Override
        public LiveBean createFromParcel(Parcel source) {
            return new LiveBean(source);
        }

        @Override
        public LiveBean[] newArray(int size) {
            return new LiveBean[size];
        }
    };
}
