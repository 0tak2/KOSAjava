package com.youngtak.kanada.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Dict {
	private String apiKey;
	public static final String baseUrl1 = "https://stdict.korean.go.kr/api/search.do?certkey_no=4720&key=";
	public static final String baseUrl2 = "&type_search=search&req_type=json&q=";
	
	Dict() {
		super();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/secret"));
			this.apiKey = br.readLine();
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public Dict(String apiKey) {
		super();
		this.apiKey = apiKey;
	}
	
	public String getWordDef(String word) {
		try {
			URL url = new URL(baseUrl1 + apiKey + baseUrl2 + word);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuffer stringBuffer = new StringBuffer();
	        
	        String inputLine;
	        while ((inputLine = bufferedReader.readLine()) != null)  {
	            stringBuffer.append(inputLine);
	        }
	        bufferedReader.close();

	        String response = stringBuffer.toString();
	        if (response == null) {
	        	return "";
	        } else {
	        	return response;	
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
