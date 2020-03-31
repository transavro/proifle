
package models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkedDevice implements Serializable, Parcelable
{

    @SerializedName("tvEmac")
    @Expose
    private String tvEmac;
    @SerializedName("tvPanel")
    @Expose
    private String tvPanel;
    @SerializedName("tvBoard")
    @Expose
    private String tvBoard;
    public final static Creator<LinkedDevice> CREATOR = new Creator<LinkedDevice>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LinkedDevice createFromParcel(Parcel in) {
            return new LinkedDevice(in);
        }

        public LinkedDevice[] newArray(int size) {
            return (new LinkedDevice[size]);
        }

    }
    ;
    private final static long serialVersionUID = -888409933593131426L;

    protected LinkedDevice(Parcel in) {
        this.tvEmac = ((String) in.readValue((String.class.getClassLoader())));
        this.tvPanel = ((String) in.readValue((String.class.getClassLoader())));
        this.tvBoard = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LinkedDevice() {
    }

    public String getTvEmac() {
        return tvEmac;
    }

    public void setTvEmac(String tvEmac) {
        this.tvEmac = tvEmac;
    }

    public LinkedDevice withTvEmac(String tvEmac) {
        this.tvEmac = tvEmac;
        return this;
    }

    public String getTvPanel() {
        return tvPanel;
    }

    public void setTvPanel(String tvPanel) {
        this.tvPanel = tvPanel;
    }

    public LinkedDevice withTvPanel(String tvPanel) {
        this.tvPanel = tvPanel;
        return this;
    }

    public String getTvBoard() {
        return tvBoard;
    }

    public void setTvBoard(String tvBoard) {
        this.tvBoard = tvBoard;
    }

    public LinkedDevice withTvBoard(String tvBoard) {
        this.tvBoard = tvBoard;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(tvEmac);
        dest.writeValue(tvPanel);
        dest.writeValue(tvBoard);
    }

    public int describeContents() {
        return  0;
    }

}
