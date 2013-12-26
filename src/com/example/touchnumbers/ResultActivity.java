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
		
		
		// �OActivity���玞�Ԃ��擾��TextView�ɕ\��
		textView.setText(getIntent().getExtras().getString("time"));
		// ������x�V�ԁ@�{�^���̏���
		replayButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						CountDownActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		// �g�b�v�ɖ߂�@�{�^���̏���
		returnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//MainActivity�ȊO�̓X�^�b�N�Ɏc���Ă��Ȃ��̂Ō�Activity���I��������ƃg�b�v�ɖ߂�
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
