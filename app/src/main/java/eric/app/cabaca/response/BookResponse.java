package eric.app.cabaca.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse extends BaseResponse implements Parcelable {
    @SerializedName("result")
    private List<Result> result;

    protected BookResponse(Parcel in) {
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
        dest.writeTypedList(result);
    }

    public static final Creator<BookResponse> CREATOR = new Creator<BookResponse>() {
        @Override
        public BookResponse createFromParcel(Parcel in) {
            return new BookResponse(in);
        }

        @Override
        public BookResponse[] newArray(int size) {
            return new BookResponse[size];
        }
    };




    public static class Result implements Parcelable{
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("cover_url")
        private String cover_url;
        @SerializedName("rate_sum")
        private String rate_sum;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        @SerializedName("book_id")
        private String book_id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getRate_sum() {
            return rate_sum;
        }

        public void setRate_sum(String rate_sum) {
            this.rate_sum = rate_sum;
        }

        protected Result(Parcel in) {
            id = in.readInt();
            title = in.readString();
            cover_url = in.readString();
            rate_sum = in.readString();
            book_id = in.readString();
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
            dest.writeInt(id);
            dest.writeString(title);
            dest.writeString(cover_url);
            dest.writeString(rate_sum);
            dest.writeString(book_id);
        }
    }
}
