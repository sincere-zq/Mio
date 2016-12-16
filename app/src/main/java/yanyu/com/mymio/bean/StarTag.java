package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class StarTag implements Parcelable {
    public String type;
    public boolean isSelect;

    public StarTag(String type, boolean isSelect) {
        this.type = type;
        this.isSelect = isSelect;
    }

    public StarTag(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StarTag{" +
                "type='" + type + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public StarTag() {
    }

    protected StarTag(Parcel in) {
        this.type = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Parcelable.Creator<StarTag> CREATOR = new Parcelable.Creator<StarTag>() {
        @Override
        public StarTag createFromParcel(Parcel source) {
            return new StarTag(source);
        }

        @Override
        public StarTag[] newArray(int size) {
            return new StarTag[size];
        }
    };
}
