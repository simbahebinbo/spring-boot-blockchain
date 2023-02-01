package com.github.lansheng228.blockchain.service.impl;

import com.github.lansheng228.blockchain.block.Block;
import com.github.lansheng228.blockchain.block.Blockchain;
import com.github.lansheng228.blockchain.response.BlocksRet;
import com.github.lansheng228.blockchain.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public BlocksRet chain() {
        int length = 0;
        try {
            Blockchain blockChain = Blockchain.initBlockchainFromDB();
            List<Block> blocks = new ArrayList<>();
            while (blockChain.getBlockchainIterator().hashNext()) {
                Block block = blockChain.getBlockchainIterator().next();
                blocks.add(block);
            }
            length = blockChain.getAllBlockHash().size();
            log.info("blocks: {}, length: {}", blocks, length);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }


        BlocksRet ret = BlocksRet.builder()
//                .shortName(cache.getShortName())
                .length(Integer.toString(length))
                .build();
        return ret;


//        if (StringUtils.isBlank(ip)) {
//            IPCountryRet ret = new IPCountryRet();
//            return ret;
//        }
//        InetAddress inetAddress = InetAddresses.forString(ip);
//        Optional<IPCountryCache> ipCountryCacheOptional = Optional.empty();
//        if (inetAddress instanceof Inet4Address) {
//            BigInteger ipValue = InetAddresses.toBigInteger(inetAddress);
//            ipCountryCacheOptional = ipCountryV4CacheService.singleIPCountryByIP(ipValue);
//        } else if (inetAddress instanceof Inet6Address) {
//            BigInteger ipValue = InetAddresses.toBigInteger(inetAddress);
//            ipCountryCacheOptional = ipCountryV6CacheService.singleIPCountryByIP(ipValue);
//        }
//
//        if (ipCountryCacheOptional.isEmpty()) {
//            IPCountryRet ret = new IPCountryRet();
//            return ret;
//        }
//
//        IPCountryCache cache = ipCountryCacheOptional.get();
//        IPCountryRet ret = IPCountryRet.builder()
//                .shortName(cache.getShortName())
//                .longName(cache.getLongName())
//                .build();
//        return ret;
    }
}
