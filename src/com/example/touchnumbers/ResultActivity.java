package com.example.touchnumbers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		TextView textView = (TextView) findViewById(R.id.resultText);
		Button returnButton = (Button) findViewById(R.id.returnButton);
		Button replayButton = (Button) findViewById(R.id.replayButton);
		
		
		// 前Activityから時間を取得しTextViewに表示
		textView.setText(getIntent().getExtras().getString("time"));
		// もう一度遊ぶ　ボタンの処理
		replayButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						CountDownActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		// トップに戻る　ボタンの処理
		returnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//MainActivity以外はスタックに残していないので現Activityを終了させるとトップに戻る
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
