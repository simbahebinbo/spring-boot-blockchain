/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.github.lansheng228.blockchain.cli;

import com.github.lansheng228.blockchain.bd.RocksDBUtils;
import com.github.lansheng228.blockchain.block.Block;
import com.github.lansheng228.blockchain.block.Blockchain;
import com.github.lansheng228.blockchain.pow.ProofOfWork;
import com.github.lansheng228.blockchain.transaction.TXOutput;
import com.github.lansheng228.blockchain.transaction.Transaction;
import com.github.lansheng228.blockchain.transaction.UTXOSet;
import com.github.lansheng228.blockchain.util.Base58Check;
import com.github.lansheng228.blockchain.wallet.Wallet;
import com.github.lansheng228.blockchain.wallet.WalletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * 命令行解析器
 *
 * @author chenhx
 * @version CLI.java, v 0.1 2018-10-15 下午 6:47
 */

@Slf4j
public class CLI {

    private final String[] args;
    private final Options options = new Options();

    public CLI(String[] args) {
        this.args = args;

        Option helpCmd = Option.builder("h").desc("show help").build();
        options.addOption(helpCmd);

        Option address = Option.builder("address").hasArg(true).desc("Source wallet address").build();
        Option sendFrom = Option.builder("from").hasArg(true).desc("Source wallet address").build();
        Option sendTo = Option.builder("to").hasArg(true).desc("Destination wallet address").build();
        Option sendAmount = Option.builder("amount").hasArg(true).desc("Amount to send").build();

        options.addOption(address);
        options.addOption(sendFrom);
        options.addOption(sendTo);
        options.addOption(sendAmount);
    }

    public static void main(String[] args) {
        CLI cli = new CLI(args);
        cli.parse();
    }

    /**
     * 命令行解析入口
     */
    public void parse() {
        this.validateArgs(args);
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            switch (args[0]) {
                case "createblockchain":
                    //创建区块
                    String createblockchainAddress = cmd.getOptionValue("address");
                    if (StringUtils.isBlank(createblockchainAddress)) {
                        help();
                    }
                    this.createBlockchain(createblockchainAddress);
                    break;
                case "mining":
                    //创建区块
                    String createblockAddress = cmd.getOptionValue("address");
                    if (StringUtils.isBlank(createblockAddress)) {
                        help();
                    }
                    this.mining(createblockAddress);
                    break;
                case "getbalance":
                    String getBalanceAddress = cmd.getOptionValue("address");
                    if (StringUtils.isBlank(getBalanceAddress)) {
                        help();
                    }
                    this.getBalance(getBalanceAddress);
                    break;
                case "send":
                    String sendFrom = cmd.getOptionValue("from");
                    String sendTo = cmd.getOptionValue("to");
                    String sendAmount = cmd.getOptionValue("amount");
                    if (StringUtils.isBlank(sendFrom) ||
                            StringUtils.isBlank(sendTo) ||
                            !NumberUtils.isDigits(sendAmount)) {
                        help();
                    }
                    this.send(sendFrom, sendTo, Integer.valueOf(sendAmount));
                    break;
                case "createwallet":
                    this.createWallet();
                    break;
                case "printaddresses":
                    this.printAddresses();
                    break;
                case "printchain":
                    this.printChain();
                    break;
                case "h":
                    this.help();
                    break;
                default:
                    this.help();
            }
        } catch (Exception e) {
            log.error("Fail to parse cli command ! ", e);
        } finally {
            RocksDBUtils.getInstance().closeDB();
        }
    }

    /**
     * 挖矿
     */
    private void mining(String address) {
        // 检查钱包地址是否合法
        try {
            Base58Check.base58ToBytes(address);
        } catch (Exception e) {
            log.error("ERROR: sender address invalid ! address=" + address, e);
            throw new RuntimeException("ERROR: sender address invalid ! address=" + address, e);
        }
        Blockchain blockchain = Blockchain.createBlockchain(address);
        //挖矿奖励
        Transaction rewardTX = Transaction.newCoinbaseTX(address, "");
        //TODO 交易需要发送到其他的节点上去  还需要获取其他的交易
        Block block = blockchain.mineBlock(new Transaction[]{rewardTX});
        new UTXOSet(blockchain).update(block);
        log.info("Done ! ");
    }

    /**
     * 创建区块链
     *
     * @param address
     */
    private void createBlockchain(String address) {
        Blockchain blockchain = Blockchain.createBlockchain(address);
        UTXOSet utxoSet = new UTXOSet(blockchain);
        utxoSet.reIndex();
        log.info("Done ! ");
    }

    /**
     * 验证入参
     *
     * @param args
     */
    private void validateArgs(String[] args) {
        if (args == null || args.length < 1) {
            help();
        }
    }

    /**
     * 创建钱包
     *
     * @throws Exception
     */
    private void createWallet() {
        Wallet wallet = WalletUtils.getInstance().createWallet();
        log.info("wallet address : " + wallet.getAddress());
    }

    /**
     * 打印钱包地址
     */
    private void printAddresses() {
        Set<String> addresses = WalletUtils.getInstance().getAddresses();
        if (addresses == null || addresses.isEmpty()) {
            log.info("There isn't address");
            return;
        }
        for (String address : addresses) {
            log.info("Wallet address: " + address);
        }
    }

    /**
     * 查询钱包余额
     *
     * @param address 钱包地址
     */
    private void getBalance(String address) {
        // 检查钱包地址是否合法
        try {
            Base58Check.base58ToBytes(address);
        } catch (Exception e) {
            log.error("ERROR: invalid wallet address", e);
            throw new RuntimeException("ERROR: invalid wallet address", e);
        }

        // 得到公钥Hash值
        byte[] versionedPayload = Base58Check.base58ToBytes(address);
        byte[] pubKeyHash = Arrays.copyOfRange(versionedPayload, 1, versionedPayload.length);

        Blockchain blockchain = Blockchain.createBlockchain(address);
        UTXOSet utxoSet = new UTXOSet(blockchain);

        TXOutput[] txOutputs = utxoSet.findUTXOs(pubKeyHash);
        int balance = 0;
        if (txOutputs != null) {
            for (TXOutput txOutput : txOutputs) {
                balance += txOutput.getValue();
            }
        }
        log.info("Balance of '{}': {}\n", address, balance);
    }

    /**
     * 转账
     *
     * @param from
     * @param to
     * @param amount
     * @throws Exception
     */
    private void send(String from, String to, int amount) throws Exception {
        // 检查钱包地址是否合法
        try {
            Base58Check.base58ToBytes(from);
        } catch (Exception e) {
            log.error("ERROR: sender address invalid ! address=" + from, e);
            throw new RuntimeException("ERROR: sender address invalid ! address=" + from, e);
        }
        // 检查钱包地址是否合法
        try {
            Base58Check.base58ToBytes(to);
        } catch (Exception e) {
            log.error("ERROR: receiver address invalid ! address=" + to, e);
            throw new RuntimeException("ERROR: receiver address invalid ! address=" + to, e);
        }
        if (amount < 1) {
            log.error("ERROR: amount invalid ! amount=" + amount);
            throw new RuntimeException("ERROR: amount invalid ! amount=" + amount);
        }
        Blockchain blockchain = Blockchain.createBlockchain(from);
        // 新交易
        Transaction transaction = Transaction.newUTXOTransaction(from, to, amount, blockchain);
        // 奖励
        Transaction rewardTx = Transaction.newCoinbaseTX(from, "");
        Block newBlock = blockchain.mineBlock(new Transaction[]{transaction, rewardTx});
        new UTXOSet(blockchain).update(newBlock);
        log.info("Success!");
    }

    /**
     * 打印帮助信息
     */
    private void help() {
        System.out.println("Usage:");
        System.out.println("  createwallet - 生成一个新的密钥对并将其保存到钱包文件中");
        System.out.println("  printaddresses - 打印所有钱包地址");
        System.out.println("  getbalance -address ADDRESS - 获取地址的余额");
        System.out.println("  createblockchain -address ADDRESS - 创建一个区块链，并向指定地址发送创世奖励");
        System.out.println("  printchain - 打印区块链的所有块");
        System.out.println("  send -from FROM -to TO -amount AMOUNT - Send AMOUNT of coins from FROM address to TO");
        System.exit(0);
    }

    /**
     * 打印出区块链中的所有区块
     */
    private void printChain() {
        Blockchain blockchain = Blockchain.initBlockchainFromDB();
        for (Blockchain.BlockchainIterator iterator = blockchain.getBlockchainIterator(); iterator.hashNext(); ) {
            Block block = iterator.next();
            if (block != null) {
                boolean validate = ProofOfWork.newProofOfWork(block).validate();
                log.info(block + ", validate = " + validate);
            }
        }
    }
}
