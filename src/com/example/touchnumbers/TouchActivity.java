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
	// ����p�̐��l
	int count = 1;
	// �{�^���ɕ\�����鐔�����i�[���郊�X�g�B
	private ArrayList<Integer> list = new ArrayList<Integer>();
	// �{�^�����i�[����z��[�{�^���̐�]
	private Button[] buttons = new Button[25];
	// id���i�[����z��ixml�Œ�`����id�j
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

		// �{�^���ɕ\�����鐔�������X�g�Ɋi�[����
		for (int i = 0; i < buttons.length; i++) {
			list.add(i + 1);
		}
		// ���X�g���V���b�t������
		Collections.shuffle(list);

		// �e�{�^����cast,setText,setOnClickListener�����[�v�Ŏ���
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);
			buttons[i].setText(list.get(i).toString());
			buttons[i].setOnClickListener(this);
		}

		// �^�C�}�[����
		/*
		 * 10msec���Ƀ^�C�}�[���񂵂Ă�CPU�̕��׏�ԂȂǂŔ����ɂ����\��������̂�
		 * SystemClock.elapsedRealtime()���g���B�iOS�N��������̎��Ԃ���Ɍv�����Ă���V�X�e��[�~���b�P�ʂŕ\��]�j
		 */
		startTime = SystemClock.elapsedRealtime();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						long nowTime = SystemClock.elapsedRealtime();
						// nowTime����startTime���������ƂŌo�ߎ��Ԃ��Z�o
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
		// �N���b�N���ꂽ�{�^����button�ɃL���X�g
		Button button = (Button) v;
		// button�ɕ\������Ă���e�L�X�g�iString�j��int�^�ɕϊ�
		int number = Integer.parseInt(button.getText().toString());

		// ����p��count�Ǝ擾����number���r������icount��==�łȂ��{�^�����N���b�N���Ă������N���Ȃ��j
		if (number == count && count == buttons.length) {
			/*
			 * 25�����������̎��Ԃ���startTime���������ƂŃN���A�^�C�����v������B
			 *�@Long�^�̃~���b�\�L�������_�\�L�̎����ɂ���ɂ́A1,000�Ŋ���B 
			 */
			long endTime = SystemClock.elapsedRealtime();
			long clearTime = endTime - startTime;
			float decimalTime = clearTime / 1000f;
			
			// �Ō�̃{�^���������ꂽ�ꍇ�ɉ�ʑJ��
			Intent intent = new Intent(TouchActivity.this, ResultActivity.class);
			intent.putExtra("time", ""+decimalTime);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		} else if (number == count) {
			// ���Ԓʂ�Ƀ{�^���������ꂽ��
			button.setEnabled(false);
			// ����p�̐��l���C���N�������g
			count++;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		timer.cancel();
	}

}
