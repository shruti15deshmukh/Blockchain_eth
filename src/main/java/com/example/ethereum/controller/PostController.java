package com.example.ethereum.controller;

import com.example.ethereum.crypto.EncryptionUtil;
import com.example.ethereum.dto.PostRequest;
import com.example.ethereum.p2p.PeerNode;
import com.example.ethereum.service.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private EthereumService ethereumService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostRequest request) {
        try {
            // Encrypt content
            String encryptedContent = EncryptionUtil.encrypt(request.getContent());

            // Send to Ethereum
            ethereumService.postToBlockchain(encryptedContent);



            return ResponseEntity.ok("Post created and broadcasted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating post: " + e.getMessage());
        }

    }


    @GetMapping("/transaction")
    public ResponseEntity<?> getTransaction(@RequestParam String hash) {
        try {
            Optional<Transaction> transactionOpt = ethereumService.getTransactionByHash(hash);

            if (transactionOpt.isPresent()) {
                Transaction tx = transactionOpt.get();

                Map<String, Object> result = new HashMap<>();
                result.put("from", tx.getFrom());
                result.put("to", tx.getTo());
                result.put("value", tx.getValue().toString());
                result.put("gas", tx.getGas().toString());
                result.put("gasPrice", tx.getGasPrice().toString());
                result.put("nonce", tx.getNonce().toString());
                result.put("hash", tx.getHash());
                result.put("blockHash", tx.getBlockHash());
                result.put("blockNumber", tx.getBlockNumber().toString());

                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving transaction: " + e.getMessage());
        }
    }
    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String address) {
        try {
            BigInteger balance = ethereumService.getBalance(address);
            return ResponseEntity.ok(Map.of(
                    "address", address,
                    "balance", balance.toString() + " Wei"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting balance: " + e.getMessage());
        }
    }
    @GetMapping("/block/transactions")
    public ResponseEntity<?> getTransactionsByBlock(@RequestParam BigInteger blockNumber) {
        try {
            List<Transaction> txList = ethereumService.getTransactionsInBlock(blockNumber);

            List<Map<String, Object>> transactions = new ArrayList<>();
            for (Transaction tx : txList) {
                Map<String, Object> map = new HashMap<>();
                map.put("hash", tx.getHash());
                map.put("from", tx.getFrom());
                map.put("to", tx.getTo());
                map.put("value", tx.getValue().toString());
                map.put("gas", tx.getGas().toString());
                transactions.add(map);
            }

            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting block transactions: " + e.getMessage());
        }
    }
    @GetMapping("/block/latest")
    public ResponseEntity<?> getLatestBlock() {
        try {
            EthBlock.Block latestBlock = ethereumService.getLatestBlock();

            Map<String, Object> blockDetails = new HashMap<>();
            blockDetails.put("number", latestBlock.getNumber().toString());
            blockDetails.put("hash", latestBlock.getHash());
            blockDetails.put("timestamp", latestBlock.getTimestamp().toString());
            blockDetails.put("transactions", latestBlock.getTransactions().size());

            List<Map<String, Object>> txList = new ArrayList<>();
            for (EthBlock.TransactionResult<?> txResult : latestBlock.getTransactions()) {
                Transaction tx = (Transaction) txResult.get();
                Map<String, Object> txData = new HashMap<>();
                txData.put("hash", tx.getHash());
                txData.put("from", tx.getFrom());
                txData.put("to", tx.getTo());
                txData.put("value", tx.getValue().toString());
                txList.add(txData);
            }

            blockDetails.put("txDetails", txList);
            return ResponseEntity.ok(blockDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching latest block: " + e.getMessage());
        }
    }





}




