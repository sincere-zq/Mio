package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class StarList implements Parcelable {
    public int star_id;

    public String starname;

    public String head_pic;

    public int follow_num;

    public int is_follow;

    @Override
    public String toString() {
        return "StarList{" +
                "star_id=" + star_id +
                ", starname='" + starname + '\'' +
                ", head_pic='" + head_pic + '\'' +
                ", follow_num=" + follow_num +
                ", is_follow=" + is_follow +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.star_id);
        dest.writeString(this.starname);
        dest.writeString(this.head_pic);
        dest.writeInt(this.follow_num);
        dest.writeInt(this.is_follow);
    }

    public StarList() {
    }

    protected StarList(Parcel in) {
        this.star_id = in.readInt();
        this.starname = in.readString();
        this.head_pic = in.readString();
        this.follow_num = in.readInt();
        this.is_follow = in.readInt();
    }

    public static final Parcelable.Creator<StarList> CREATOR = new Parcelable.Creator<StarList>() {
        @Override
        public StarList createFromParcel(Parcel source) {
            return new StarList(source);
        }

        @Override
        public StarList[] newArray(int size) {
            return new StarList[size];
        }
    };
}
