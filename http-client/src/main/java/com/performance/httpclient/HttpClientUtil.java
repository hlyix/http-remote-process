package com.performance.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class HttpClientUtil {

   final static CloseableHttpClient m_HttpClient = HttpClients.createDefault();


    public static byte[] post(String url, String contentType) throws IOException {
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setEntity(new ByteArrayEntity(bytes));
        if (contentType != null)
            httpPost.setHeader("Content-type", contentType);
        CloseableHttpResponse httpResponse = m_HttpClient.execute(httpPost);
        try {
            HttpEntity entityResponse = httpResponse.getEntity();
            int contentLength = (int) entityResponse.getContentLength();
            if (contentLength <= 0)
                throw new IOException("No response");
            byte[] respBuffer = new byte[contentLength];

            if (entityResponse.getContent().read(respBuffer) >= respBuffer.length)
                throw new IOException("Read response buffer error");
            return respBuffer;
        } finally {
            httpResponse.close();
        }
    }
}
