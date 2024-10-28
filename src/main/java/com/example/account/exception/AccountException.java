package com.example.account.exception;

import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public AccountException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
// checkedException은 항상 메소드에 줄줄이 Exception을 붙이고 다니거나
// 기본적으로 checked Exception은 transactional을 rollback이 되지 않음
// 그래서 checkedException은 잘 사용하지 않음
