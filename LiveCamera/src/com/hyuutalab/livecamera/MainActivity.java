package com.hyuutalab.livecamera;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InitLayout();
		
	}
	
	private void StartIntervalShutter(){
		Intent serviceIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent
		        = PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.setInexactRepeating (
		        AlarmManager.RTC,
		        System.currentTimeMillis(),
		        //AlarmManager.INTERVAL_HOUR,
		        AlarmManager.INTERVAL_FIFTEEN_MINUTES,
		        pendingIntent);
	}
	
	
	private void InitLayout(){
		final Button btnStart = (Button)findViewById(R.id.main_btnStart);
		final Button btnEnd = (Button)findViewById(R.id.main_btnEnd);
		final Button btnAddPrefixDT = (Button)findViewById(R.id.main_btnPrefixCurDT);
		final Button btnClearPrefix = (Button)findViewById(R.id.main_btnPrefixClear);
		final EditText edPrefix = (EditText)findViewById(R.id.main_txtPrefix);
		
		btnStart.setOnClickListener(new OnClickListener(){@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}});
		
		btnEnd.setOnClickListener(new OnClickListener(){public void onClick(View v) {
			
		}});
		
		btnAddPrefixDT.setOnClickListener(new OnClickListener(){public void onClick(View v) {
			Calendar cal = Calendar.getInstance();
			String s = String.valueOf(cal.get(Calendar.YEAR)) +
					String.format("%02d",cal.get(Calendar.MONTH)) +
					String.format("%02d",cal.get(Calendar.DATE)) +
					"_" + 
					String.format("%02d",cal.get(Calendar.HOUR_OF_DAY)) +
					String.format("%02d",cal.get(Calendar.MINUTE));
			
			edPrefix.setText(s + edPrefix.getText());
		}});
		
		btnClearPrefix.setOnClickListener(new OnClickListener(){public void onClick(View v) {
			edPrefix.setText("");
		}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
