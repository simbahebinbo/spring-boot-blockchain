package com.github.lansheng228.blockchain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RFSException extends RuntimeException {
    private static final long serialVersionUID = 4907339762891790110L;

    public RFSException(String message) {
        super(message);
    }

    public RFSException(Throwable cause) {
        super(cause);
    }

    public RFSException(String message, Throwable cause) {
        super(message, cause);
    }

    public RFSException(RFSError error) {
        super(error.getMessage() + " (" + error.getCode() + ")");
    }
}
