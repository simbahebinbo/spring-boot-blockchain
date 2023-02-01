package com.github.lansheng228.blockchain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RFSError {

    private Integer code;

    private String message;
}
