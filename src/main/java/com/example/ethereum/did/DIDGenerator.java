package com.example.ethereum.did;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

// DIDGenerator.java
public class DIDGenerator {
    public static String generateDID() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        String publicKeyBase64 = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
        return "did:example:" + publicKeyBase64.substring(0, 20);
    }
}

