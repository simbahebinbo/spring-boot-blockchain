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
 * 交易输出
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TXOutputRet implements Serializable {

    /**
     * 数值
     */
    @JsonProperty("value")
    private String value;
    
    /**
     * 公钥Hash
     */
    @JsonProperty("pub-key-hash")
    private String pubKeyHash;
}
