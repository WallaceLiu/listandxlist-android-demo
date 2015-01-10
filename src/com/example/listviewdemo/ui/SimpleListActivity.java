package com.example.listviewdemo.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.listviewdemo.R;
import com.example.listviewdemo.adapter.SimpleListAdapter;
import com.example.listviewdemo.dao.MusicDao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class SimpleListActivity extends Activity {

	private ListView list;
	private SimpleListAdapter adapter;
	private MusicDao music;
	private JSONArray jsonArray;

	/* Button btn; */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simplelist);

		initClass();

		InitListView();

		new MyTask().execute(music);
	}

	private void initClass() {
		music = new MusicDao();
	}

	private void InitListView() {
		list = (ListView) findViewById(R.id.myList_a);
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
					adapter = new SimpleListAdapter(SimpleListActivity.this,
							jsonArray);
					list.setAdapter(adapter);
					list.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							JSONObject jObject;
							try {
								jObject = (JSONObject) jsonArray.get(position);
								Toast.makeText(getApplicationContext(),
										jObject.getString("artist"),
										Toast.LENGTH_SHORT).show();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					list.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							JSONObject jObject;
							try {
								jObject = (JSONObject) jsonArray.get(position);
								Toast.makeText(getApplicationContext(),
										jObject.getString("artist"),
										Toast.LENGTH_SHORT).show();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
