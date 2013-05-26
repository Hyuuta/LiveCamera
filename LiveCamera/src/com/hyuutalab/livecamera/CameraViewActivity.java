package com.hyuutalab.livecamera;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraViewActivity extends Activity implements PictureCallback, ShutterCallback, AutoFocusCallback,SurfaceHolder.Callback{
	
	Camera camera;
	SurfaceView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cameraview);
		sv =(SurfaceView)findViewById(R.id.camview_suf_main);
		sv.getHolder().addCallback(this);
//		
		camera = Camera.open();
		Camera.Parameters parameters = camera.getParameters();
		List<Size> s= parameters.getSupportedPreviewSizes();
		
		try {
			camera.setPreviewDisplay(sv.getHolder());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camera.startPreview();
		
		
		parameters.setPreviewSize(s.get(0).width,  s.get(0).height);
		camera.setParameters(parameters);
		
//		Intent intent = getIntent();
//		intent.getIntExtra(AppKey.CUR_STEP, 0);
//		String start = intent.getStringExtra(AppKey.START_TIME);
//		Date date= new Date(Date.parse(start));
//		Calendar cal = Calendar.getInstance();
//		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		camera.release();
	}

	@Override
	public void onAutoFocus(boolean arg0, Camera arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPictureTaken(byte[] arg0, Camera arg1) {
		 
	}

	@Override
	public void onShutter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	
	
}
