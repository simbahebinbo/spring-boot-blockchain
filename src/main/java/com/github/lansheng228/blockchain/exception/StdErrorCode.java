package com.github.lansheng228.blockchain.exception;

import java.io.Serializable;

public interface StdErrorCode extends Serializable {

    int getCode();

    /**
     * Get error code as String.
     *
     * @return Error code like "gateway-url-notfound"
     */
    String getDesc();

    /**
     * Get error message for debug purpose.
     *
     * @return Debug message like "Requested URL not found"
     */
    default String getErrMsg() {
        return String.valueOf(getDesc());
    }

    default String getErrCode() {
        return String.valueOf(getCode());
    }
}
