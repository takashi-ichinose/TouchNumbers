package com.example.touchnumbers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						CountDownActivity.class);
				// CountDownActivity�̓X�^�b�N�Ɏc���K�v���Ȃ��̂�FLAG_ACTIVITY_NO_HISTORY���Z�b�g����B
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
	}
}
