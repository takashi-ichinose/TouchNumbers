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

		// �J�E���g�_�E���^�C�}�[������
		time = 3;
		// �w�莞�Ԗ��ɏ������J��Ԃ�
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (time > 0) {
							// time�̒l��0���傫����΃e�L�X�g������������time���f�N�������g
							countText.setText(time + "");
							time--;
						} else {
							// time�̒l��0�ɂȂ������ʑJ��
							Intent intent = new Intent(CountDownActivity.this,
									TouchActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							startActivity(intent);
						}

					}
				});
			}
		}, 0, 1000);//������s�܂ł̎��ԁi0�j,2��ڈȍ~���s�̊Ԋu�i1000�b/m = 1�b�j
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
