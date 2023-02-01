package com.github.lansheng228.blockchain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Invariant {
    public static void invariant(Boolean condition, String message) {
        if (!condition) {
            throw new RFSException(message);
        }
    }

    public static void invariant(Boolean condition, StdErrorCode error) {
        if (!condition) {
            throw new StdApiException(error);
        }
    }

    public static void invariant(Boolean condition, StdErrorCode error, String message) {

        if (!condition) {
            log.warn(message);
            throw new StdApiException(error);
        } else {
            log.info(message);
        }
    }

    public static void invariant(Boolean condition) {
        if (!condition) {
            throw new RFSException();
        }
    }
}
