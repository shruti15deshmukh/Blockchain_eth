package com.example.ethereum.controller;

import com.example.ethereum.crypto.EncryptionUtil;
import com.example.ethereum.dto.PostRequest;
import com.example.ethereum.p2p.PeerNode;
import com.example.ethereum.service.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//            // Optional: P2P Broadcast
//            PeerNode peerNode = new PeerNode(8085); // Simulate peer
//            peerNode.broadcastPost("127.0.0.1", 8086, encryptedContent); // Send to another peer

            return ResponseEntity.ok("Post created and broadcasted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating post: " + e.getMessage());
        }
    }
}

