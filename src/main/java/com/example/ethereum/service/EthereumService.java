package com.example.ethereum.service;

import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;
import com.example.ethereum.SocialMedia;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


// EthereumService.java
@Service
public class EthereumService {
    private final Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8550")); // Ganache
    private final Credentials credentials = Credentials.create("0x54fb428f9e9d500e113f410a61fee88ec6b3a012c44c2b2fc15d4480dc871ae7");

    public void postToBlockchain(String encryptedContent) throws Exception {

        SocialMedia contract = SocialMedia.load(
                "0x82ad2E236709a13499f20e25CC30992A6ED6cA5e"
                , web3j, credentials, new DefaultGasProvider()
        );

        TransactionReceipt receipt = contract.createPost(encryptedContent).send();
        System.out.println("Transaction Hash: " + receipt.getTransactionHash());
    }
    public Optional<Transaction> getTransactionByHash(String hash) throws Exception {
        EthTransaction ethTransaction = web3j.ethGetTransactionByHash(hash).send();
        return ethTransaction.getTransaction();
    }
    public BigInteger getBalance(String address) throws Exception {
        EthGetBalance ethGetBalance = web3j
                .ethGetBalance(address, DefaultBlockParameterName.LATEST)
                .send();
        return ethGetBalance.getBalance();
    }
    public List<Transaction> getTransactionsInBlock(BigInteger blockNumber) throws Exception {
        EthBlock blockResponse = web3j.ethGetBlockByNumber(
                        DefaultBlockParameter.valueOf(blockNumber), true)
                .send();

        return blockResponse.getBlock().getTransactions().stream()
                .map(txResult -> (Transaction) txResult.get())
                .toList();
    }
    public EthBlock.Block getLatestBlock() throws Exception {
        EthBlock ethBlock = web3j.ethGetBlockByNumber(
                DefaultBlockParameterName.LATEST, true
        ).send();
        return ethBlock.getBlock();
    }




}



