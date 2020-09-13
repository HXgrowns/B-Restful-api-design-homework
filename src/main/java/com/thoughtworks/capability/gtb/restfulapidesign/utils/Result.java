package com.thoughtworks.capability.gtb.restfulapidesign.utils;

import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;

    private Result(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public static Result error(int code, String message) {
        return Result.builder()
                .code(code)
                .message(message)
                .build();
    }
}
