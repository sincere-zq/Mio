package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class CollectNewsList implements Parcelable {
    public int news_id;

    public String title;

    public String intro;

    public String cover;

    public String source;

    public String datetime;

    public int view_num;

    public int agree_num;

    public int comment_num;

    @Override
    public String toString() {
        return "CollectNewsList{" +
                "news_id=" + news_id +
                ", title='" + title + '\'' +
                ", content='" + intro + '\'' +
                ", cover='" + cover + '\'' +
                ", source='" + source + '\'' +
                ", datetime='" + datetime + '\'' +
                ", view_num=" + view_num +
                ", agree_num=" + agree_num +
                ", comment_num=" + comment_num +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.news_id);
        dest.writeString(this.title);
        dest.writeString(this.intro);
        dest.writeString(this.cover);
        dest.writeString(this.source);
        dest.writeString(this.datetime);
        dest.writeInt(this.view_num);
        dest.writeInt(this.agree_num);
        dest.writeInt(this.comment_num);
    }

    public CollectNewsList() {
    }

    protected CollectNewsList(Parcel in) {
        this.news_id = in.readInt();
        this.title = in.readString();
        this.intro = in.readString();
        this.cover = in.readString();
        this.source = in.readString();
        this.datetime = in.readString();
        this.view_num = in.readInt();
        this.agree_num = in.readInt();
        this.comment_num = in.readInt();
    }

    public static final Creator<CollectNewsList> CREATOR = new Creator<CollectNewsList>() {
        @Override
        public CollectNewsList createFromParcel(Parcel source) {
            return new CollectNewsList(source);
        }

        @Override
        public CollectNewsList[] newArray(int size) {
            return new CollectNewsList[size];
        }
    };
}
