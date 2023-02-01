package com.github.lansheng228.blockchain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 交易
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRet implements Serializable {
    /**
     * 交易的Hash
     */
    @JsonProperty("tx-id")
    private String txId;

    /**
     * 交易输入
     */
    @JsonProperty("inputs")
    private List<TXInputRet> inputs;

    /**
     * 交易输出
     */
    @JsonProperty("outputs")
    private List<TXOutputRet> outputs;

    /**
     * 创建日期
     */
    @JsonProperty("create-time")
    private String createTime;
}
