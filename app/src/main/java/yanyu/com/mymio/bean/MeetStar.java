package yanyu.com.mymio.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

public class MeetStar implements Parcelable {
    public int meeting_id;
    public String title;
    public String address;
    public String time_hold;
    public int min;
    public int day_rest;
    public int ticket_all;
    public int ticket_rest;
    public int is_index;
    public List<String> index_pic;

    @Override
    public String toString() {
        return "MeetStar{" +
                "meeting_id=" + meeting_id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", time_hold='" + time_hold + '\'' +
                ", min=" + min +
                ", day_rest=" + day_rest +
                ", ticket_all=" + ticket_all +
                ", ticket_rest=" + ticket_rest +
                ", is_index=" + is_index +
                ", index_pic=" + index_pic +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.meeting_id);
        dest.writeString(this.title);
        dest.writeString(this.address);
        dest.writeString(this.time_hold);
        dest.writeInt(this.min);
        dest.writeInt(this.day_rest);
        dest.writeInt(this.ticket_all);
        dest.writeInt(this.ticket_rest);
        dest.writeInt(this.is_index);
        dest.writeStringList(this.index_pic);
    }

    public MeetStar() {
    }

    protected MeetStar(Parcel in) {
        this.meeting_id = in.readInt();
        this.title = in.readString();
        this.address = in.readString();
        this.time_hold = in.readString();
        this.min = in.readInt();
        this.day_rest = in.readInt();
        this.ticket_all = in.readInt();
        this.ticket_rest = in.readInt();
        this.is_index = in.readInt();
        this.index_pic = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MeetStar> CREATOR = new Parcelable.Creator<MeetStar>() {
        @Override
        public MeetStar createFromParcel(Parcel source) {
            return new MeetStar(source);
        }

        @Override
        public MeetStar[] newArray(int size) {
            return new MeetStar[size];
        }
    };
}
