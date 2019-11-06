package eric.app.cabaca.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreResponse implements Parcelable {
    @SerializedName("resource")
    private List<Resource> resource;

    protected GenreResponse(Parcel in) {
        resource = in.createTypedArrayList(Resource.CREATOR);
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(resource);
    }

    public static final Creator<GenreResponse> CREATOR = new Creator<GenreResponse>() {
        @Override
        public GenreResponse createFromParcel(Parcel in) {
            return new GenreResponse(in);
        }

        @Override
        public GenreResponse[] newArray(int size) {
            return new GenreResponse[size];
        }
    };




    public static class Resource implements Parcelable{
        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        protected Resource(Parcel in) {
            id = in.readString();
            title = in.readString();
        }

        public static final Creator<Resource> CREATOR = new Creator<Resource>() {
            @Override
            public Resource createFromParcel(Parcel in) {
                return new Resource(in);
            }

            @Override
            public Resource[] newArray(int size) {
                return new Resource[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
        }
    }
}
