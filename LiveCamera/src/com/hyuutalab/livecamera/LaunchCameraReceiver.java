package com.hyuutalab.livecamera;




import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.sax.StartElementListener;
public class LaunchCameraReceiver extends BroadcastReceiver  {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Intent startCamera = new Intent(arg0, CameraViewActivity.class);
		startCamera.putExtra(AppKey.CUR_STEP, arg1.getIntExtra(AppKey.CUR_STEP, 0));
		
		arg0.startActivity(startCamera);
	
	}


	

}
