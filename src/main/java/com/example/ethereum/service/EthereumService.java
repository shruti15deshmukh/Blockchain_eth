package com.example.ethereum.service;

import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import com.example.ethereum.SocialMedia;


// EthereumService.java
@Service
public class EthereumService {
    private final Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8550")); // Ganache
    private final Credentials credentials = Credentials.create("0x45e25781201e34dd29ff70b4cb535dc07f2a2e2e71313d381506b96fdd163e30");

    public void postToBlockchain(String encryptedContent) throws Exception {

        SocialMedia contract = SocialMedia.load(
                "0x68D3Ef6add3173e87ee30AAD68e06b7aB83EDF1F"
                , web3j, credentials, new DefaultGasProvider()
        );

        TransactionReceipt receipt = contract.createPost(encryptedContent).send();
        System.out.println("Transaction Hash: " + receipt.getTransactionHash());
    }
}



