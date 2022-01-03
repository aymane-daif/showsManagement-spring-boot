package daif.aymane.showsManagement.users;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
    private boolean success;
    private String message;
    private List<UserResponse> data = new ArrayList<>();

    public ResponseObject() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserResponse> getData() {
        return data;
    }

    public void setData(List<UserResponse> data) {
        this.data = data;
    }
}
