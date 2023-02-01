package com.github.lansheng228.blockchain.exception;

import com.github.lansheng228.blockchain.common.CommonConstant;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class StdApiException extends RuntimeException {

    private static final long serialVersionUID = 3702360675190838718L;

    /**
     * 错误码.
     */
    private final StdErrorCode stdErrorCode;

    @Getter
    private final HashMap<String, Object> extraData = Maps.newHashMap();

    /**
     * ErrorCode
     */
    public StdApiException(StdErrorCode stdErrorCode) {
        super(stdErrorCode.getErrMsg());
        this.stdErrorCode = stdErrorCode;
    }

    public StdApiException(StdErrorCode stdErrorCode, String message) {
        super(stdErrorCode.getErrMsg() + CommonConstant.SEPARATOR_HYPHEN + message);
        this.stdErrorCode = stdErrorCode;
    }


    /**
     * Throwable
     */
    public StdApiException(StdErrorCode stdErrorCode, Throwable t) {
        super(t);
        this.stdErrorCode = stdErrorCode;
    }

    public int getCode() {
        return stdErrorCode.getCode();
    }

    public String getDesc() {
        return stdErrorCode.getDesc();
    }

    public StdApiException withExtraData(Map<String, Object> extra) {
        this.extraData.putAll(extra);
        return this;
    }
}

