
package models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkedUsers implements Serializable, Parcelable
{

    @SerializedName("user")
    @Expose
    private List<User> user = null;
    public final static Creator<LinkedUsers> CREATOR = new Creator<LinkedUsers>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LinkedUsers createFromParcel(Parcel in) {
            return new LinkedUsers(in);
        }

        public LinkedUsers[] newArray(int size) {
            return (new LinkedUsers[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7528394371211255305L;

    protected LinkedUsers(Parcel in) {
        in.readList(this.user, (User.class.getClassLoader()));
    }

    public LinkedUsers() {
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public LinkedUsers withUser(List<User> user) {
        this.user = user;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(user);
    }

    public int describeContents() {
        return  0;
    }

}
