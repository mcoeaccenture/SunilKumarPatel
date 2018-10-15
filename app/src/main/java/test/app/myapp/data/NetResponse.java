package test.app.myapp.data;

public class NetResponse<T> {
    public enum Status {SUCCESS, ERROR, PROGRESS}

    private final Status status;

    private final T data;

    private final String message;

    public NetResponse(T data, Status status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}
