package com.example.listviewdemo.ui;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.listviewdemo.R;
import com.example.listviewdemo.adapter.SimpleListAdapter;
import com.example.listviewdemo.dao.MusicDao;
import com.example.listviewdemo.widget.XListView;
import com.example.listviewdemo.widget.XListView.IXListViewListener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class XListAnotherActivity extends Activity implements
		IXListViewListener {

	private XListView mListView;
	private SimpleListAdapter mAdapter;
	private JSONArray jsonArray;
	private JSONArray items;
	private MusicDao music;

	private Handler mHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xlistanother);

		initClass();

		initControl();

		initXList();

		new MyTask().execute(music);
	}

	private void initClass() {
		music = new MusicDao();
		mHandler = new Handler();
		items = new JSONArray();
	}

	private void initControl() {
		mListView = (XListView) findViewById(R.id.xListView_c);
		mAdapter = new SimpleListAdapter(XListAnotherActivity.this, items);
	}

	private void initXList() {
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(false);
		mListView.setXListViewListener(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					Toast.makeText(getApplicationContext(),
							items.get(position).toString(), Toast.LENGTH_SHORT)
							.show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					Toast.makeText(getApplicationContext(),
							items.get(position).toString(), Toast.LENGTH_SHORT)
							.show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				for (int i = 9; i >= 0; i--) {
					try {
						items.put(jsonArray.get(i));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				new MyTask().execute(music);
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
				try {
					for (int i = 0; i < 10; i++) {
						items.put(jsonArray.get(i));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// mAdapter.notifyDataSetChanged();
				new MyTask().execute(music);
				onLoad();
			}
		}, 2000);
	}

	public class MyTask extends AsyncTask<MusicDao, String, String> {

		private boolean mUseCache;

		public MyTask() {
			mUseCache = true;
		}

		public MyTask(boolean useCache) {
			mUseCache = useCache;
		}

		/* 执行前，相关页面处理 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		/* 执行中 */
		@Override
		protected String doInBackground(MusicDao... params) {
			MusicDao dao = params[0];
			String result = dao.getData();
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (!result.isEmpty()) {
				try {
					jsonArray = new JSONArray(result);
					jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						items.put(jsonArray.get(i));
					}
					mAdapter.notifyDataSetChanged();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}