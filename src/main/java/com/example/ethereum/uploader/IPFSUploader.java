package com.example.ethereum.uploader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;


public class IPFSUploader {
    public static String upload(byte[] data) throws IOException {
        HttpPost post = new HttpPost("https://ipfs.infura.io:5001/api/v0/add");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", data, ContentType.DEFAULT_BINARY, "post.txt");
        post.setEntity(builder.build());

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {

            String responseJson = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(responseJson);
            return json.getString("Hash");
        }
    }
}

