package id.kostfinder.app.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;
}
