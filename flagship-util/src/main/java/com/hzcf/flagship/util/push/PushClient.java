package com.hzcf.flagship.util.push;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

@SuppressWarnings("deprecation")
public class PushClient {
	
	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";
	
	private Logger logger=LoggerFactory.getLogger(getClass()); 

	// This object is used for sending the post request to Umeng
	protected HttpClient client = new DefaultHttpClient(); 

	public Map<String, Object> send(String url, String param) throws Exception {
		HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        StringEntity se = new StringEntity(param, "UTF-8");
        post.setEntity(se);
        // Send the post request and get the response
        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        if (status == 200) {
        	logger.info(result.toString());
        	logger.info("Notification sent successfully.");
        } else {
        	logger.error(result.toString());
        	logger.error("Failed to send the notification!");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result.toString());
        map.put("status", status);
        return map;
    }

	 

}
