package com.hyuutalab.livecamera;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.DataSetObserver;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends Activity{
	Button btnStart;
	Button btnEnd;
	Button btnAddPrefixDT;
	Button btnClearPrefix;
	Button btnPreview;
	EditText edPrefix;
	SharedPreferences pref;
	
	int currentStep;
	boolean isRunning;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InitLayout();
		pref = getSharedPreferences("LiveCamera", MODE_PRIVATE);
		
		readState(pref);
		
	}

	private void InitLayout(){
		btnStart = (Button)findViewById(R.id.main_btnStart);
		btnEnd = (Button)findViewById(R.id.main_btnEnd);
		btnAddPrefixDT = (Button)findViewById(R.id.main_btnPrefixCurDT);
		btnClearPrefix = (Button)findViewById(R.id.main_btnPrefixClear);
		btnPreview = (Button)findViewById(R.id.main_btnPreview);
		
		edPrefix = (EditText)findViewById(R.id.main_txtPrefix);
		
		
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
		
		
		btnPreview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO
				launchPreview();
				;
			}
		});


	}
	
	private void launchPreview(){
		Intent intent = new Intent(this, CameraViewActivity.class);
		startActivity(intent);
	}
	
	private void readState(SharedPreferences pref) {
		// TODO Auto-generated method stub
		isRunning =pref.getBoolean("isRunning", false); 
		btnStart.setEnabled(!isRunning);
		btnEnd.setEnabled(isRunning);
		btnAddPrefixDT.setEnabled(!isRunning);
		btnClearPrefix.setEnabled(!isRunning);
		
	}

	private void startIntervalShutter(){
		Intent serviceIntent = new Intent(this, LaunchCameraReceiver.class);
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
	
	@Override
	protected void onStop(){
		super.onStop();
		Editor ed = pref.edit();
		ed.putBoolean(AppKey.ISRUN, isRunning);
		ed.commit();
		
	}
	



}
