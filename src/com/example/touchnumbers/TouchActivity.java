package com.example.touchnumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TouchActivity extends Activity implements OnClickListener {
	// 判定用の数値
	int count = 1;
	// ボタンに表示する数字を格納するリスト。
	private ArrayList<Integer> list = new ArrayList<Integer>();
	// ボタンを格納する配列[ボタンの数]
	private Button[] buttons = new Button[25];
	// idを格納する配列（xmlで定義したid）
	private int[] ids = new int[] { R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.button5, R.id.button6, R.id.button7,
			R.id.button8, R.id.button9, R.id.button10, R.id.button11,
			R.id.button12, R.id.button13, R.id.button14, R.id.button15,
			R.id.button16, R.id.button17, R.id.button18, R.id.button19,
			R.id.button20, R.id.button21, R.id.button22, R.id.button23,
			R.id.button24, R.id.button25 };

	private TextView timerText;
	private Timer timer = new Timer(true);
	private Handler handler = new Handler();
	private long startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);

		timerText = (TextView) findViewById(R.id.timerView);

		// ボタンに表示する数字をリストに格納する
		for (int i = 0; i < buttons.length; i++) {
			list.add(i + 1);
		}
		// リストをシャッフルする
		Collections.shuffle(list);

		// 各ボタンにcast,setText,setOnClickListenerをループで実装
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);
			buttons[i].setText(list.get(i).toString());
			buttons[i].setOnClickListener(this);
		}

		// タイマー処理
		/*
		 * 10msec毎にタイマーを回してもCPUの負荷状態などで微妙にずれる可能性があるので
		 * SystemClock.elapsedRealtime()を使う。（OS起動時からの時間を常に計測しているシステム[ミリ秒単位で表示]）
		 */
		startTime = SystemClock.elapsedRealtime();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						long nowTime = SystemClock.elapsedRealtime();
						// nowTimeからstartTimeを引くことで経過時間を算出
						long diffTime = nowTime - startTime;
						float decimalTime = diffTime / 1000f;
						timerText.setText("" + decimalTime);
					}
				});
			}
		}, 10, 10);

	}

	@Override
	public void onClick(View v) {
		// クリックされたボタンをbuttonにキャスト
		Button button = (Button) v;
		// buttonに表示されているテキスト（String）をint型に変換
		int number = Integer.parseInt(button.getText().toString());

		// 判定用のcountと取得したnumberを比較し分岐（countと==でないボタンをクリックしても何も起きない）
		if (number == count && count == buttons.length) {
			/*
			 * 25を押した時の時間からstartTimeを引くことでクリアタイムを計測する。
			 *　Long型のミリ秒表記を小数点表記の時刻にするには、1,000で割る。 
			 */
			long endTime = SystemClock.elapsedRealtime();
			long clearTime = endTime - startTime;
			float decimalTime = clearTime / 1000f;
			
			// 最後のボタンが押された場合に画面遷移
			Intent intent = new Intent(TouchActivity.this, ResultActivity.class);
			intent.putExtra("time", ""+decimalTime);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		} else if (number == count) {
			// 順番通りにボタンが押された時
			button.setEnabled(false);
			// 判定用の数値をインクリメント
			count++;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		timer.cancel();
	}

}
