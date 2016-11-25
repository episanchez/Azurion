package com.azurion.launcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpRequest {
	private HttpClient client;
	private UsernamePasswordCredentials creds;
	
	public HttpRequest(String username, String password){
		//JOptionPane.showMessageDialog(null, "befor HTTP builder", "HTTP Request", JOptionPane.ERROR_MESSAGE);
		client = HttpClientBuilder.create().build();
		//JOptionPane.showMessageDialog(null, "After HTTP Builder", "HTTP Request", JOptionPane.ERROR_MESSAGE);
		creds =  new UsernamePasswordCredentials(username, password);
	}
	
	
	public String get(String URL){
		//JOptionPane.showMessageDialog(null, "HTTPGet", "HTTP Request", JOptionPane.ERROR_MESSAGE);
		HttpGet httpGet = new HttpGet(URL);
		try {
			//JOptionPane.showMessageDialog(null, "Before Basic Scheme", "HTTP Request", JOptionPane.ERROR_MESSAGE);
			Header header = new BasicScheme(StandardCharsets.UTF_8).authenticate(creds , httpGet, null);
			//JOptionPane.showMessageDialog(null, "After Basic Scheme", "Information", JOptionPane.ERROR_MESSAGE);
			httpGet.addHeader(header);
			
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			
			return responseString;
			
		} catch (AuthenticationException e) {
			JOptionPane.showMessageDialog(null, "Connexion à échoué : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "Connexion à échoué : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return "Unauthorized";
	}
	
	public String post(String URL){
		HttpPost httpPost = new HttpPost(URL);
		try{
			Header header = new BasicScheme(StandardCharsets.UTF_8).authenticate(creds , httpPost, null);
			httpPost.addHeader(header);
			
			HttpResponse httpResponse = client.execute(httpPost);
			HttpEntity responseEntity = httpResponse.getEntity();
			
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			
			return responseString;
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return "Unauthorized";
	}


}
