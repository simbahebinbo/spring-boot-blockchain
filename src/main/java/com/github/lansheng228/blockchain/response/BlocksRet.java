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
public class BlocksRet implements Serializable {
    @JsonProperty("blocks")
    private List<BlockRet> blocks;

    @JsonProperty("length")
    private String length;
}


