package kr.co.yapp.cafe.ui;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("성공"),
    FAILURE("실패"),
    ;

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }
}
