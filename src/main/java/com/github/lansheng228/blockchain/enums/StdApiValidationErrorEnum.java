package com.github.lansheng228.blockchain.enums;

import com.github.lansheng228.blockchain.exception.StdErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StdApiValidationErrorEnum implements StdErrorCode {
    SYSTEM_EXCEPTION(7001, "system exception"),

    INVALID_PARAM(7002, "invalid param"),


    // 请求数太多
    TOO_MANY_REQUESTS(429, "too many requests");

    private final int code;

    private final String desc;
}


