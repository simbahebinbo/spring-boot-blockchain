package com.github.lansheng228.blockchain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 交易输入
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TXInputRet implements Serializable {
    /**
     * 交易Id的hash值
     * 包含了它所指向的UTXO的交易的Hash值。
     */
    @JsonProperty("tx-id")
    private String txId;

    /**
     * 交易输出索引
     * 定义了它所指向的UTXO在上一笔交易中交易输出数组的位置。
     */
    @JsonProperty("tx-output-index")
    private String txOutputIndex;

    /**
     * 签名
     */
    @JsonProperty("signature")
    private String signature;

    /**
     * 公钥
     */
    @JsonProperty("pub-key")
    private String pubKey;
}
