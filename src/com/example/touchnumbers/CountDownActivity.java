package com.example.touchnumbers;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class CountDownActivity extends Activity {
	private TextView countText;
	private int time;
	private Handler handler = new Handler();
	private Timer timer = new Timer(true);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_down);

		countText = (TextView) findViewById(R.id.countView);

		// カウントダウンタイマーを実装
		time = 3;
		// 指定時間毎に処理を繰り返す
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (time > 0) {
							// timeの値が0より大きければテキストを書き換え後timeをデクリメント
							countText.setText(time + "");
							time--;
						} else {
							// timeの値が0になったら画面遷移
							Intent intent = new Intent(CountDownActivity.this,
									TouchActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							startActivity(intent);
						}

					}
				});
			}
		}, 0, 1000);//初回実行までの時間（0）,2回目以降実行の間隔（1000秒/m = 1秒）
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_down, menu);
		return true;
	}
	@Override
	protected void onPause() {
		super.onPause();
		timer.cancel();
	}

}
