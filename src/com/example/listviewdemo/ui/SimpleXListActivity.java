package com.example.listviewdemo.ui;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.listviewdemo.R;
import com.example.listviewdemo.widget.XListView;
import com.example.listviewdemo.widget.XListView.IXListViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class SimpleXListActivity extends Activity implements IXListViewListener {
	private XListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simplexlist);

		geneItems();

		initClass();

		initControl();

		initXList();

	}

	private void geneItems() {
		for (int i = 0; i != 20; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	private void initClass() {
		mHandler = new Handler();
	}

	private void initControl() {
		mListView = (XListView) findViewById(R.id.xListView_a);
		mAdapter = new ArrayAdapter<String>(this, R.layout.xlist_simpleitem,
				items);
	}

	private void initXList() {
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(true);
		mListView.setAdapter(mAdapter);
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(getApplicationContext(), items.get(position),
						Toast.LENGTH_SHORT).show();
			}
		});
		mListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), items.get(position),
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	// Refresh
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(SimpleXListActivity.this,
						R.layout.xlist_simpleitem, items);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	// LoadMore
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

}