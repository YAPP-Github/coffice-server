package kr.co.yapp._22nd.coffice.ui;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ApiResponse<T> {
    @NotNull
    private final String code;
    @NotNull
    private final String message;
    private final T data;
    private final PageResponse page;

    private ApiResponse(ResultCode code, String message, T data, PageResponse page) {
        this.code = code.name();
        this.message = message;
        this.data = data;
        this.page = page;
    }

    private ApiResponse(ResultCode code, T data, PageResponse pageResponse) {
        this(code, code.getMessage(), data, pageResponse);
    }

    private ApiResponse(ResultCode code, T data) {
        this(code, code.getMessage(), data, null);
    }

    private ApiResponse(ResultCode code, String message) {
        this(code, message, null, null);
    }

    private ApiResponse(ResultCode code) {
        this(code, code.getMessage(), null, null);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResultCode.SUCCESS, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(ResultCode.SUCCESS, data);
    }

    public static <T> ApiResponse<List<T>> success(List<T> list) {
        return new ApiResponse<>(ResultCode.SUCCESS, list);
    }

    public static <T> ApiResponse<List<T>> success(Slice<T> slice) {
        return new ApiResponse<>(
                ResultCode.SUCCESS,
                slice.getContent(),
                PageResponse.from(slice)
        );
    }

    public static <T> ApiResponse<List<T>> success(Page<T> page) {
        return new ApiResponse<>(ResultCode.SUCCESS, page.getContent());
    }

    public static <T> ApiResponse<T> failure() {
        return new ApiResponse<T>(ResultCode.FAILURE);
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode) {
        return new ApiResponse<>(resultCode);
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode, String message) {
        return new ApiResponse<>(resultCode, message);
    }

}
