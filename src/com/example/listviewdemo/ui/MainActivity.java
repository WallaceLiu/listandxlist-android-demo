package com.example.listviewdemo.ui;

import com.example.listviewdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Description:
 * 
 * Date 2014-08-17
 * 
 * @author LN liuning800203@aliyun.com
 * @version 1.0
 */
public class MainActivity extends Activity {
	Intent intent = null;

	private Button b1, b2, b3, b4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		initControl();
		initButton();
	}

	private void initControl() {
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
	}

	private void initButton() {
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this, SimpleListActivity.class);
				startActivity(intent);
			}
		});

		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this,
						SimpleXListActivity.class);
				startActivity(intent);
			}
		});

		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this, XListActivity.class);
				startActivity(intent);
			}
		});

		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this,
						XListAnotherActivity.class);
				startActivity(intent);
			}
		});
	}
}
