package eric.app.cabaca.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class WriterResponse implements Parcelable {
    @SerializedName("result")
    private List<Result> result;

    protected WriterResponse(Parcel in) {
        result = in.createTypedArrayList(Result.CREATOR);
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<WriterResponse> CREATOR = new Creator<WriterResponse>() {
        @Override
        public WriterResponse createFromParcel(Parcel in) {
            return new WriterResponse(in);
        }

        @Override
        public WriterResponse[] newArray(int size) {
            return new WriterResponse[size];
        }
    };

    public static class Result implements Parcelable{
        @SerializedName("user_id")
        private Integer user_id;
        @SerializedName("count_follower")
        private Integer count_follower;
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("photo_url")
        private String photo_url;


        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public Integer getCount_follower() {
            return count_follower;
        }

        public void setCount_follower(Integer count_follower) {
            this.count_follower = count_follower;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        protected Result(Parcel in) {
            user_id = in.readInt();
            count_follower = in.readInt();
            name = in.readString();
            username = in.readString();
            photo_url = in.readString();
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(user_id);
            dest.writeInt(count_follower);
            dest.writeString(name);
            dest.writeString(username);
            dest.writeString(photo_url);
        }
    }
}
