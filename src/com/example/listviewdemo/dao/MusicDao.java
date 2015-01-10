package com.example.listviewdemo.dao;

import com.example.listviewdemo.config.Urls;
import com.example.listviewdemo.utils.CustomHttpUrlConnection;

public class MusicDao {

	public MusicDao() {

	}

	public String getData() {
		CustomHttpUrlConnection rq = new CustomHttpUrlConnection();
		String result = rq.getJsonFromUrl(Urls.HTTP_URL);
		return result;
	}
}
