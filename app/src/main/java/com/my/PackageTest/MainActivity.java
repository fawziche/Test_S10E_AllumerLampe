package com.my.PackageTest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity 
{
	private CameraManager cameraManager;
	private String cameraId;
	private boolean isFlashOn = false;
	private Button toggleFlashButton;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		// Recupere mon btn
		toggleFlashButton = findViewById(R.id.toggleFlashButton);
		
		// Recherche l'ID de la camera
		try {
			cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
			cameraId = cameraManager.getCameraIdList()[0]; // Prend le premier appareil photo
		} 
		catch (CameraAccessException e) {
			e.printStackTrace();
		}
		
		// Definit l'action de mon btn
		toggleFlashButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleFlashlight();
			}
		});
    }
	
	private void toggleFlashlight() {
		try {
			if (isFlashOn) {
				turnOffFlashlight();
			} else {
				turnOnFlashlight();
			}
		} 
		catch (CameraAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void turnOnFlashlight() throws CameraAccessException {
		cameraManager.setTorchMode(cameraId, true);
		isFlashOn = true;
		toggleFlashButton.setText("Éteindre la lampe");
	}
	
	private void turnOffFlashlight() throws CameraAccessException {
		cameraManager.setTorchMode(cameraId, false);
		isFlashOn = false;
		toggleFlashButton.setText("Allumer la lampe");
	}
}