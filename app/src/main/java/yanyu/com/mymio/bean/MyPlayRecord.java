package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zq on 2016/10/31 0031.
 */

public class MyPlayRecord  implements Parcelable {
    public int video_id;

    public String title;

    public String cover;

    public String datetime;

    public int duration;

    public int view_num;

    public int agree_num;

    public int comment_num;

    public int wpuser_id;

    public String username;

    @Override
    public String toString() {
        return "MyPlayRecord{" +
                "video_id=" + video_id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", datetime='" + datetime + '\'' +
                ", duration=" + duration +
                ", view_num=" + view_num +
                ", agree_num=" + agree_num +
                ", comment_num=" + comment_num +
                ", wpuser_id=" + wpuser_id +
                ", username='" + username + '\'' +
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
        dest.writeInt(this.view_num);
        dest.writeInt(this.agree_num);
        dest.writeInt(this.comment_num);
        dest.writeInt(this.wpuser_id);
        dest.writeString(this.username);
    }

    public MyPlayRecord() {
    }

    protected MyPlayRecord(Parcel in) {
        this.video_id = in.readInt();
        this.title = in.readString();
        this.cover = in.readString();
        this.datetime = in.readString();
        this.duration = in.readInt();
        this.view_num = in.readInt();
        this.agree_num = in.readInt();
        this.comment_num = in.readInt();
        this.wpuser_id = in.readInt();
        this.username = in.readString();
    }

    public static final Creator<MyPlayRecord> CREATOR = new Creator<MyPlayRecord>() {
        @Override
        public MyPlayRecord createFromParcel(Parcel source) {
            return new MyPlayRecord(source);
        }

        @Override
        public MyPlayRecord[] newArray(int size) {
            return new MyPlayRecord[size];
        }
    };
}
