package kr.co.yapp.cafe.ui;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ApiResponse<T> {
    private final String code;
    private final String message;
    private final T data;

    private ApiResponse(ResultCode code) {
        this.code = code.name();
        this.message = code.getMessage();
        this.data = null;
    }

    private ApiResponse(ResultCode code, T data) {
        this.code = code.name();
        this.message = code.getMessage();
        this.data = data;
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

    // TODO: 무한스크롤 대응 위한 id 기반 페이징 필요
    public static <T> ApiResponse<List<T>> success(Page<T> page) {
        return new ApiResponse<>(ResultCode.SUCCESS, page.getContent());
    }

    public static <T> ApiResponse<T> failure() {
        return new ApiResponse<T>(ResultCode.FAILURE, null);
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode) {
        return new ApiResponse<T>(resultCode, null);
    }
}
