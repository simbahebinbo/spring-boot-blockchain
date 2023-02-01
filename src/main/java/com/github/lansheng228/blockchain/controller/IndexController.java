package com.github.lansheng228.blockchain.controller;

import com.github.lansheng228.blockchain.enums.StdApiValidationErrorEnum;
import com.github.lansheng228.blockchain.exception.StdApiException;
import com.github.lansheng228.blockchain.response.ApiExtResponse;
import com.github.lansheng228.blockchain.response.BlocksRet;
import com.github.lansheng228.blockchain.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Validated
@RestController
@RequestMapping("ui")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 用于输出整个区块链的数据
     */
    @RequestMapping("chain")
    public ApiExtResponse<BlocksRet> chain() {
        log.info("chain param: ");

        try {
            BlocksRet ret = indexService.chain();
            log.info("chain success. ret: {}", ret);
            return ApiExtResponse.ok(ret);
        } catch (StdApiException e) {
            log.warn("chain error with custom. exception: {}", e.getMessage(), e);
            return ApiExtResponse.error(e.getCode(), e.getDesc());
        } catch (Exception e) {
            log.warn("chain error without custom. exception: {}", e.getMessage(), e);
            return ApiExtResponse.error(
                    StdApiValidationErrorEnum.SYSTEM_EXCEPTION.getCode(),
                    StdApiValidationErrorEnum.SYSTEM_EXCEPTION.getDesc());
        }
    }

    /**
     * 用于接收并处理新的交易信息
     */
    @RequestMapping("transactions/new")
    public void transactionsNew() {

//        req.setCharacterEncoding("utf-8");
//        // 读取客户端传递过来的数据并转换成JSON格式
//        BufferedReader reader = req.getReader();
//        String input = null;
//        StringBuffer requestBody = new StringBuffer();
//        while ((input = reader.readLine()) != null) {
//            requestBody.append(input);
//        }
//        JSONObject jsonValues = new JSONObject(requestBody.toString());
//
//        // 检查所需要的字段是否位于POST的data中
//        String[] required = {"sender", "recipient", "amount"};
//        for (String string : required) {
//            if (!jsonValues.has(string)) {
//                // 如果没有需要的字段就返回错误信息
//                resp.sendError(400, "Missing values");
//            }
//        }
//
//        // 新建交易信息
////        Blockchain blockChain = Blockchain.getInstance();
////        int index = blockChain.newTransactions(jsonValues.getString("sender"), jsonValues.getString("recipient"),
////                jsonValues.getLong("amount"));
//        int index = 1;
//
//        // 返回json格式的数据给客户端
//        resp.setContentType("application/json");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println(new JSONObject().append("message", "Transaction will be added to Block " + index));
//        printWriter.close();
    }

    /**
     * 用于运行工作算法的证明来获得下一个证明，也就是所谓的挖矿
     */
    @RequestMapping("mine")
    public void mine() {
//        Blockchain blockChain = Blockchain.getInstance();
//        Map<String, Object> lastBlock = blockChain.lastBlock();
//        long lastProof = Long.parseLong(lastBlock.get("proof") + "");
//        long proof = blockChain.proofOfWork(lastProof);
//
//        // 给工作量证明的节点提供奖励，发送者为 "0" 表明是新挖出的币
//        String uuid = (String) req.getServletContext().getAttribute("uuid");
//        blockChain.newTransactions("0", uuid, 1);
//
//        // 构建新的区块
//        Map<String, Object> newBlock = blockChain.newBlock(proof, null);
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "New Block Forged");
//        response.put("index", newBlock.get("index"));
//        response.put("transactions", newBlock.get("transactions"));
//        response.put("proof", newBlock.get("proof"));
//        response.put("previous_hash", newBlock.get("previous_hash"));
//
//        // 返回新区块的数据给客户端
//        resp.setContentType("application/json");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println(new JSONObject(response));
//        printWriter.close();
    }

}
