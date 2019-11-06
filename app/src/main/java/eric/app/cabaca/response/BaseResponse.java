package eric.app.cabaca.response;

import com.google.gson.annotations.SerializedName;


public class BaseResponse {
    @SerializedName("success")
    protected String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
