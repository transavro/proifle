package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TvEmac implements Serializable, Parcelable
{

    @SerializedName("emac")
    @Expose
    private String emac;
    public final static Parcelable.Creator<TvEmac> CREATOR = new Creator<TvEmac>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TvEmac createFromParcel(Parcel in) {
            return new TvEmac(in);
        }

        public TvEmac[] newArray(int size) {
            return (new TvEmac[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1582263057715292212L;

    protected TvEmac(Parcel in) {
        this.emac = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TvEmac() {
    }

    public String getEmac() {
        return emac;
    }

    public void setEmac(String emac) {
        this.emac = emac;
    }

    public TvEmac withEmac(String emac) {
        this.emac = emac;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(emac);
    }

    public int describeContents() {
        return 0;
    }

}
