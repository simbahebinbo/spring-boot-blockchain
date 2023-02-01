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


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BlockRet implements Serializable {
    /**
     * 版本
     */
    @JsonProperty("version")
    private String version;

    /**
     * 区块hash值
     */
    @JsonProperty("hash")
    private String hash;
    /**
     * 前一个区块的hash值
     */
    @JsonProperty("prev-block-hash")
    private String prevBlockHash;

    /**
     * 交易信息
     */
    @JsonProperty("transactions")
    private List<TransactionRet> transactions;

    /**
     * 区块创建时间(单位:毫秒)
     */
    @JsonProperty("timestamp")
    private String timestamp;

    /**
     * 工作量证明计数器 挖到时的运算值
     */
    @JsonProperty("nonce")
    private String nonce;

    /**
     * 区块高度
     */
    @JsonProperty("height")
    private String height;

    /**
     * 默克尔树rootHash
     */
    @JsonProperty("merkle-root")
    private String merkleRoot;

    /**
     * 难度目标值
     */
    @JsonProperty("target")
    private String target;
}
