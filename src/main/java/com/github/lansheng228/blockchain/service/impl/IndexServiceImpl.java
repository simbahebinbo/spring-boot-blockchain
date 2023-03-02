package com.github.lansheng228.blockchain.service.impl;

import com.github.lansheng228.blockchain.block.Block;
import com.github.lansheng228.blockchain.block.Blockchain;
import com.github.lansheng228.blockchain.response.BlockRet;
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
        List<BlockRet> blockRets = new ArrayList<>();

        try {
            Blockchain blockChain = Blockchain.initBlockchainFromDB();
            List<Block> blocks = new ArrayList<>();
            while (blockChain.getBlockchainIterator().hashNext()) {
                Block block = blockChain.getBlockchainIterator().next();
                blocks.add(block);
            }
            length = blockChain.getAllBlockHash().size();
            blocks.forEach(b -> {
                BlockRet br = new BlockRet();
                br.setVersion(br.getVersion());
                br.setHash(b.getHash());


//                /**
//                 * 前一个区块的hash值
//                 */
//                @JsonProperty("prev-block-hash")
//                private String prevBlockHash;
//
//                /**
//                 * 交易信息
//                 */
//                @JsonProperty("transactions")
//                private List<TransactionRet> transactions;
//
//                /**
//                 * 区块创建时间(单位:毫秒)
//                 */
//                @JsonProperty("timestamp")
//                private String timestamp;
//
//                /**
//                 * 工作量证明计数器 挖到时的运算值
//                 */
//                @JsonProperty("nonce")
//                private String nonce;
//
//                /**
//                 * 区块高度
//                 */
//                @JsonProperty("height")
//                private String height;
//
//                /**
//                 * 默克尔树rootHash
//                 */
//                @JsonProperty("merkle-root")
//                private String merkleRoot;
//
//                /**
//                 * 难度目标值
//                 */
//                @JsonProperty("target")
//                private String target;

                blockRets.add(br);
            });
            log.info("blocks: {}, length: {}", blocks, length);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }


        BlocksRet ret = BlocksRet.builder()
                .blocks(blockRets)
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
