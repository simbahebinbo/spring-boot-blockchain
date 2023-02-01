package com.github.lansheng228.blockchain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiExtResponse<T> {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int code;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    /**
     * Build a successful response.
     *
     * @param data Response data.
     */
    public static <K> ApiExtResponse<K> ok(K data) {
        ApiExtResponse<K> response = new ApiExtResponse<>();
        response.success = true;
        response.data = data;
        return response;
    }


    public static <K> ApiExtResponse<K> error(Integer errCode, String errMsg) {
        ApiExtResponse<K> response = new ApiExtResponse<>();
        response.success = false;
        response.code = errCode;
        response.message = errMsg;
        return response;
    }
}
