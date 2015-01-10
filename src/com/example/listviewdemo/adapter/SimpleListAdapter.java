package com.example.listviewdemo.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.listviewdemo.R;
import com.example.listviewdemo.utils.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleListAdapter extends BaseAdapter {

	private Activity activity;
	private JSONArray data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public SimpleListAdapter(Activity a, JSONArray jsonArr) {
		activity = a;
		data = jsonArr;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
		
	}

	public int getCount() {
		return data == null ? 0 : data.length();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null);

		JSONObject song = null;

		ImageView image = (ImageView) vi.findViewById(R.id.imagethumb);
		TextView tvartist = (TextView) vi.findViewById(R.id.artist);
		TextView tvtitle = (TextView) vi.findViewById(R.id.title);
		TextView tvduration = (TextView) vi.findViewById(R.id.duration);

		try {
			song = data.getJSONObject(position);
			imageLoader.DisplayImage(song.getString("thumb_url"), image);
			tvartist.setText(song.getString("artist"));
			tvtitle.setText(song.getString("title"));
			tvduration.setText(song.getString("duration"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vi;
	}
}