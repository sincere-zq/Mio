package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

public class Banner implements Parcelable {
    public String md5;
    public String type;
    public int jump_id;
    public String extra;

    @Override
    public String toString() {
        return "Banner{" +
                "md5='" + md5 + '\'' +
                ", type='" + type + '\'' +
                ", jump_id=" + jump_id +
                ", extra='" + extra + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.md5);
        dest.writeString(this.type);
        dest.writeInt(this.jump_id);
        dest.writeString(this.extra);
    }

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.md5 = in.readString();
        this.type = in.readString();
        this.jump_id = in.readInt();
        this.extra = in.readString();
    }

    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };
}
