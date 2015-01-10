package com.example.listviewdemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

public class CustomHttpUrlConnection {

	public CustomHttpUrlConnection() {
	}

	public String getJsonFromUrl(String urlStr) {

		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// Sets the flag indicating whether this URLConnection allows input.
			// conn.setDoInput(true);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			// Flag to define whether the protocol will automatically follow
			// redirects or not.
			conn.setInstanceFollowRedirects(true);

			int response_code = conn.getResponseCode();
			if (response_code == 200) {
				InputStream in = conn.getInputStream();
				InputStreamReader inputReader = new InputStreamReader(in);
				BufferedReader buffReader = new BufferedReader(inputReader);

				String line = "", JsonStr = "";
				while ((line = buffReader.readLine()) != null) {
					JsonStr += line;
				}
				// JSONArray jsonArray = new JSONArray(JsonStr);
				// return jsonArray;
				return JsonStr;
			} else {
				conn.disconnect();
				return null;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
